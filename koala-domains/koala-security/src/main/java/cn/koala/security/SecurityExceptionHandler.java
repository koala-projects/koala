package cn.koala.security;

import cn.koala.web.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 安全异常处理器
 *
 * @author Houtaroy
 */
@Order(400)
@RestControllerAdvice
@Slf4j
public class SecurityExceptionHandler {
  /**
   * 默认的异常处理
   *
   * @param e 异常信息
   * @return 错误结果
   */
  @ExceptionHandler(value = AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Response exception(Exception e) {
    LOGGER.error(e.getMessage(), e);
    return Response.FORBIDDEN;
  }
}
