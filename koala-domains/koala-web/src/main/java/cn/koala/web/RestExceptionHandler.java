package cn.koala.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


/**
 * 异常处理器
 *
 * @author Houtaroy
 */
@Order(500)
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
  public Response exception(Exception e) {
    LOGGER.error("错误", e);
    return Response.error("服务器错误, 请联系管理员");
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response exception(IllegalArgumentException e) {
    LOGGER.error("错误", e);
    return Response.error(e.getMessage());
  }

  @ExceptionHandler(value = IllegalStateException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response exception(IllegalStateException e) {
    LOGGER.error("错误", e);
    return Response.error(e.getMessage());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response defaultErrorHandler(MethodArgumentNotValidException e) {
    LOGGER.error("参数校验失败", e);
    return Response.error(getFieldErrorDefaultMessages(e));
  }

  protected String getFieldErrorDefaultMessages(MethodArgumentNotValidException e) {
    return e.getBindingResult().getFieldErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.joining(", "));
  }
}
