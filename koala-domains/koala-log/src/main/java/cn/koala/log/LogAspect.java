package cn.koala.log;

import cn.koala.log.annotations.Log;
import cn.koala.log.services.LogService;
import cn.koala.persist.domain.YesNo;
import cn.koala.toolkit.DateHelper;
import cn.koala.toolkit.HttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 日志切面
 *
 * @author Houtaroy
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
@Order(3300)
public class LogAspect {
  private static final Long UNKNOWN_USER_ID = -1L;

  private final SpelExpressionParser parser = new SpelExpressionParser();

  private final TemplateParserContext parserContext = new TemplateParserContext("${", "}");

  private final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
  

  private final List<String> ignoredPatterns;

  private final ObjectProvider<AuditorAware<?>> auditorAware;

  private final LogService logService;

  private final ObjectMapper objectMapper;

  @Around("@annotation(log)")
  public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
    if (isIgnored(log.module())) {
      return joinPoint.proceed();
    }
    long start = System.currentTimeMillis();
    LogEntity entity = obtainLogEntity(joinPoint, log);
    try {
      Object result = joinPoint.proceed();
      entity.setIsSucceeded(YesNo.YES);
      entity.setResponse(toJson(result));
      return result;
    } catch (Throwable e) {
      entity.setIsSucceeded(YesNo.NO);
      entity.setError(e.getLocalizedMessage());
      throw e;
    } finally {
      entity.setCost(System.currentTimeMillis() - start);
      this.logService.create(entity);
    }
  }

  protected boolean isIgnored(String module) {
    return this.ignoredPatterns.stream().anyMatch(pattern -> Pattern.matches(pattern, module));
  }

  protected LogEntity obtainLogEntity(@NonNull JoinPoint joinPoint, @NonNull Log log) {
    return LogEntity.builder()
      .module(log.module())
      .content(getLogContent(log.content(), joinPoint))
      .userId(getAuditor())
      .userIp(HttpHelper.getRequestIp())
      .request(toJson(joinPoint.getArgs()))
      .logTime(DateHelper.now())
      .build();
  }

  protected String getLogContent(@NonNull String content, @NonNull JoinPoint joinPoint) {
    String result = content;
    if (joinPoint.getSignature() instanceof MethodSignature methodSignature) {
      Method method = methodSignature.getMethod();
      Object[] args = joinPoint.getArgs();
      Object target = joinPoint.getTarget();
      LogExpressionRootObject rootObject = new LogExpressionRootObject(method, args, target, AopProxyUtils.ultimateTargetClass(target));
      MethodBasedEvaluationContext context = new MethodBasedEvaluationContext(rootObject, method, args, parameterNameDiscoverer);
      result = parser.parseExpression(content, parserContext).getValue(context, String.class);
    }
    return result;
  }

  protected Long getAuditor() {
    AuditorAware<?> aware = auditorAware.getIfAvailable();
    if (aware == null) {
      return UNKNOWN_USER_ID;
    }
    return aware.getCurrentAuditor()
      .filter(auditor -> auditor instanceof Long)
      .map(Long.class::cast)
      .orElse(UNKNOWN_USER_ID);
  }

  protected String toJson(Object data) {
    try {
      return this.objectMapper.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      return "数据转换为JSON失败: %s".formatted(e.getLocalizedMessage());
    }
  }
}
