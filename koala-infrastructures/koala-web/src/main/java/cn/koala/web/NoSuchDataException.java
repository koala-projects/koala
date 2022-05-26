package cn.koala.web;

/**
 * @author Houtaroy
 */
public class NoSuchDataException extends RuntimeException {
  /**
   * 构造函数
   *
   * @param message 异常信息
   */
  public NoSuchDataException(String message) {
    super(message);
  }
}
