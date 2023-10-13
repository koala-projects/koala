package cn.koala.persist;

/**
 * 系统实体监听器
 *
 * @author Houtaroy
 */
public interface SystemEntityListener {

  default boolean support(Class<?> entityClass) {
    return true;
  }
}
