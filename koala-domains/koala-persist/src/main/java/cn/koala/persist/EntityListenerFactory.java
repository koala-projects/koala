package cn.koala.persist;

import java.util.List;

/**
 * 实体监听器工厂
 *
 * @author Houtaroy
 */
public interface EntityListenerFactory {

  List<Object> getEntityListeners(Class<?> entityClass);
}
