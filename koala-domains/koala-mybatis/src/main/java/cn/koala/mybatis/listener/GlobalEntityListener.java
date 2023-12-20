package cn.koala.mybatis.listener;

/**
 * 全局实体监听器
 *
 * @author Houtaroy
 */
public interface GlobalEntityListener {

  default boolean support(Class<?> entityClass) {
    return true;
  }
}
