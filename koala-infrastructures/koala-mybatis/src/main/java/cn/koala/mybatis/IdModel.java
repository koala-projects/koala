package cn.koala.mybatis;

/**
 * Id模型
 *
 * @param <T> id类型
 * @author Houtaroy
 */
public interface IdModel<T> {
  /**
   * 获取id
   *
   * @return id
   */
  T getId();

  /**
   * 设置id
   *
   * @param id id
   */
  void setId(T id);

  /**
   * 如果id不存在则设置
   *
   * @param id id
   */
  default void setIdIfAbsent(T id) {
    if (getId() == null) {
      setId(id);
    }
  }
}
