package cn.houtaroy.koala.models;

/**
 * @param <IdType> 主键类型
 * @author Houtaroy
 */
public interface Idable<IdType> {

  /**
   * 获取主键
   *
   * @return 主键
   */
  IdType getId();
}
