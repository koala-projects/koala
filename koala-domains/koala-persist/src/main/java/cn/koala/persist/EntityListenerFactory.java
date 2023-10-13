package cn.koala.persist;

import java.util.List;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public interface EntityListenerFactory {

  List<Object> getEntityListeners(Class<?> entityClass);
}
