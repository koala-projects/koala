package cn.koala.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Rest风格异常处理器
 *
 * @author Houtaroy
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

  /**
   * 默认的异常处理
   *
   * @param e 异常信息
   * @return 错误结果
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response common(Exception e) {
    LOGGER.error(e.getMessage(), e);
    return Response.error(e.getMessage());
  }
}
