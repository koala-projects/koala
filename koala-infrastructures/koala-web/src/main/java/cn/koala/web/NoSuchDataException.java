package cn.koala.web;

/**
 * @author Houtaroy
 */
public class NoSuchDataException extends RuntimeException {

  /**
   * 没有找到数据异常
   *
   * @param id 数据id
   */
  public NoSuchDataException(Object id) {
    super(String.format("数据[id=%s]不存在", id));
  }
}
