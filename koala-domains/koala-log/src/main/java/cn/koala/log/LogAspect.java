package cn.koala.log;

import cn.koala.log.annotations.Log;
import cn.koala.log.services.LogService;
import cn.koala.mybatis.CrudHelper;
import cn.koala.mybatis.YesNo;
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
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
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
  private final LogService logService;
  private final ObjectMapper objectMapper;
  private final LogProperties properties;
  private final SpelExpressionParser parser = new SpelExpressionParser();
  private final TemplateParserContext parserContext = new TemplateParserContext("${", "}");
  private final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

  @Around(value = "@annotation(log)")
  public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
    if (isIgnored(log)) {
      return joinPoint.proceed();
    }
    long start = System.currentTimeMillis();
    LogEntity logEntity = getLogEntity(joinPoint, log);
    Object result;
    try {
      result = joinPoint.proceed();
      doSuccess(logEntity, result);
      return result;
    } catch (Throwable e) {
      doThrowable(logEntity, e);
      throw e;
    } finally {
      logEntity.setCost(System.currentTimeMillis() - start);
      logService.add(logEntity);
    }
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
      .userId(CrudHelper.getAuditorId())
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

  protected void doSuccess(LogEntity log, Object result) {
    log.setIsSucceeded(YesNo.YES);
    log.setResponse(object2json(result, "序列化日志响应失败"));
  }

  protected void doThrowable(LogEntity log, Throwable e) {
    log.setIsSucceeded(YesNo.NO);
    log.setError(e.getMessage());
  }

  protected String object2json(Object data, String errorMessage) {
    try {
      return objectMapper.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      LOGGER.error(errorMessage, e);
    }
    return null;
  }
}
