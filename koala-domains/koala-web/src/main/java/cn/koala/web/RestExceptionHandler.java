package cn.koala.web;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response exception(IllegalArgumentException e) {
    LOGGER.error("错误", e);
    return Response.error(e.getMessage());
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response exception(IllegalStateException e) {
    LOGGER.error("错误", e);
    return Response.error(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    LOGGER.error("参数校验失败", e);
    return Response.error(getAllErrorDefaultMessages(e));
  }

  protected String getAllErrorDefaultMessages(MethodArgumentNotValidException e) {
    return e.getBindingResult().getAllErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.joining(", "));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response constraintViolationExceptionHandler(ConstraintViolationException e) {
    LOGGER.error("参数校验失败", e);
    return Response.error(getConstraintViolationMessages(e));
  }

  protected String getConstraintViolationMessages(ConstraintViolationException e) {
    return e.getConstraintViolations().stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.joining(", "));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response exception(Exception e) {
    LOGGER.error("错误", e);
    return Response.error("服务器错误, 请联系管理员");
  }
}
