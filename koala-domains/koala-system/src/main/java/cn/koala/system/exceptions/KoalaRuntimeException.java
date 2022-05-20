package cn.koala.system.exceptions;

/**
 * @author Houtaroy
 */
public class KoalaRuntimeException extends RuntimeException {
  /**
   * 构造函数
   *
   * @param message 异常信息
   */
  public KoalaRuntimeException(String message) {
    super(message);
  }
}
