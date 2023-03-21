package cn.koala.persist.listener;

/**
 * 实体监听器
 *
 * @author Houtaroy
 */
public interface EntityListener {
  void beforeAdd(Object entity);

  void afterAdd(Object entity);

  void beforeUpdate(Object entity);

  void afterUpdate(Object entity);

  void beforeDelete(Object entity);

  void afterDelete(Object entity);
}
