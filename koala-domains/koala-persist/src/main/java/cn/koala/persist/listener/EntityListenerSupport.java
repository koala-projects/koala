package cn.koala.persist.listener;

import java.util.List;

/**
 * 监听器支持接口
 *
 * @author Houtaroy
 */
public interface EntityListenerSupport<T> {
  Class<T> getEntityClass();

  void registerListener(EntityListener<? super T> listener);

  void registerListeners(List<EntityListener<? super T>> listeners);
}
