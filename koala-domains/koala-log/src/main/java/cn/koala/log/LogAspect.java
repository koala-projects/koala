package cn.koala.log;

import cn.koala.log.annotations.Log;
import cn.koala.log.services.LogService;
import cn.koala.mybatis.CrudHelper;
import cn.koala.toolkit.DateHelper;
import cn.koala.toolkit.HttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * 日志切面
 *
 * @author Houtaroy
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogAspect {

  private final ThreadLocal<Long> cost = new ThreadLocal<>();
  private final LogService logService;
  private final ObjectMapper objectMapper;

  @Before("@annotation(log)")

  public void before(Log log) {
    cost.set(System.currentTimeMillis());
  }

  @AfterReturning(value = "@annotation(log)", returning = "result")
  public void afterReturning(JoinPoint joinPoint, Log log, Object result) {
    logService.add(LogEntity.builder()
      .module(log.module())
      .content(log.content())
      .logTime(DateHelper.now())
      .userId(CrudHelper.getAuditorId())
      .userIp(getRequestIp())
      .request(object2json(joinPoint.getArgs(), "序列化日志请求失败"))
      .response(object2json(result, "序列化日志响应失败"))
      .cost(System.currentTimeMillis() - this.cost.get())
      .build());
  }

  @AfterThrowing(value = "@annotation(log)", throwing = "e")
  public void afterThrowing(JoinPoint joinPoint, Log log, Exception e) {

  }

  protected String getRequestIp() {
    return Optional.of(RequestContextHolder.currentRequestAttributes())
      .filter(attributes -> attributes instanceof ServletRequestAttributes)
      .map(ServletRequestAttributes.class::cast)
      .map(ServletRequestAttributes::getRequest)
      .map(HttpHelper::getRequestIp)
      .orElse(null);
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
