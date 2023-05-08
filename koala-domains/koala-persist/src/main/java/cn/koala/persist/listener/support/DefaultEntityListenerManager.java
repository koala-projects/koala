package cn.koala.persist.listener.support;

import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.EntityListenerManager;
import cn.koala.persist.listener.EntityListenerWrapper;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体监听器管理器默认实现
 *
 * @author Houtaroy
 */
public class DefaultEntityListenerManager implements EntityListenerManager {

  protected final List<EntityListener> listeners;
  protected final Map<Class<?>, List<EntityListenerWrapper>> cache = new HashMap<>();

  public DefaultEntityListenerManager(List<EntityListener> listeners) {
    this.listeners = new ArrayList<>(listeners);
  }

  public List<EntityListenerWrapper> getListeners(Class<?> entityClass) {
    if (entityClass == null) {
      return Collections.emptyList();
    }
    if (cache.containsKey(entityClass)) {
      return cache.get(entityClass);
    }
    return cache.computeIfAbsent(entityClass, k -> doGetListeners(entityClass));
  }

  protected List<EntityListenerWrapper> doGetListeners(@NonNull Class<?> entityClass) {
    return listeners.stream()
      .filter(listener -> listener.support(entityClass)).map(EntityListenerWrapper::from)
      .toList();
  }
}
