package cn.koala.web;

/**
 * 业务异常
 *
 * @author Houtaroy
 */
public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }
}
