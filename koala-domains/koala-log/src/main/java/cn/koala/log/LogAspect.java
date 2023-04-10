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
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.domain.AuditorAware;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 日志切面
 *
 * @author Houtaroy
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogAspect {
  private static final Long UNKNOWN_USER_ID = -1L;
  private final LogProperties properties;
  private final LogService logService;
  private final ObjectProvider<AuditorAware<?>> auditorAware;
  private final ObjectMapper objectMapper;
  private final ThreadLocal<Long> startTime = new ThreadLocal<>();
  private final SpelExpressionParser parser = new SpelExpressionParser();
  private final TemplateParserContext parserContext = new TemplateParserContext("${", "}");
  private final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

  @Before(value = "@annotation(log)")
  public void before(JoinPoint joinPoint, Log log) {
    startTime.set(System.currentTimeMillis());
  }

  @AfterReturning(value = "@annotation(log)", returning = "response")
  public void afterReturning(JoinPoint joinPoint, Log log, Object response) {
    if (isIgnored(log)) {
      return;
    }
    LogEntity logEntity = getLogEntity(joinPoint, log);
    logEntity.setIsSucceeded(YesNo.YES);
    logEntity.setResponse(object2json(response, "序列化日志响应失败"));
    logEntity.setCost(getCost());
    logService.add(logEntity);
  }

  @AfterThrowing(value = "@annotation(log)", throwing = "throwing")
  public void afterThrowing(JoinPoint joinPoint, Log log, Exception throwing) {
    if (isIgnored(log)) {
      return;
    }
    LogEntity logEntity = getLogEntity(joinPoint, log);
    logEntity.setIsSucceeded(YesNo.NO);
    logEntity.setError(throwing.getMessage());
    logEntity.setCost(getCost());
    logService.add(logEntity);
  }

  protected boolean isIgnored(Log log) {
    boolean result = false;
    for (String pattern : properties.getIgnoredPatterns()) {
      result = Pattern.matches(pattern, log.module());
      if (result) {
        break;
      }
    }
    return result;
  }

  protected LogEntity getLogEntity(@NonNull JoinPoint joinPoint, @NonNull Log log) {
    return LogEntity.builder()
      .module(log.module())
      .content(getLogContent(log.content(), joinPoint))
      .userId(getAuditor())
      .userIp(HttpHelper.getRequestIp())
      .request(object2json(joinPoint.getArgs(), "序列化日志请求失败"))
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

  protected String object2json(Object data, String errorMessage) {
    try {
      return objectMapper.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      LOGGER.error(errorMessage, e);
    }
    return null;
  }

  protected Long getCost() {
    return Optional.ofNullable(startTime.get())
      .map(start -> System.currentTimeMillis() - start)
      .orElse(null);
  }
}
