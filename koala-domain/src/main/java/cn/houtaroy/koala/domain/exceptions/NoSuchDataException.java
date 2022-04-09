package cn.houtaroy.koala.domain.exceptions;

/**
 * @author Houtaroy
 */
public class NoSuchDataException extends KoalaRuntimeException {

  /**
   * 构造函数
   *
   * @param message 异常信息
   */
  public NoSuchDataException(String message) {
    super(message);
  }
}
