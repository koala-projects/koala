package cn.koala.persist.support;

import cn.koala.persist.EntityListener;
import cn.koala.persist.EntityListenerManager;
import cn.koala.persist.EntityListenerWrapper;
import lombok.NonNull;
import org.springframework.core.OrderComparator;
import org.springframework.util.Assert;

import java.util.ArrayList;
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
    this.listeners = new ArrayList<>(listeners.stream().sorted(OrderComparator.INSTANCE).toList());
  }

  public List<EntityListenerWrapper> getListeners(@NonNull Object entity) {
    Assert.notNull(entity, "实体不能为null");
    Class<?> entityClass = entity.getClass();
    if (cache.containsKey(entityClass)) {
      return cache.get(entityClass);
    }
    return cache.computeIfAbsent(entityClass, k -> doGetListeners(entity));
  }

  protected List<EntityListenerWrapper> doGetListeners(@NonNull Object entity) {
    return listeners.stream().filter(listener -> listener.support(entity)).map(EntityListenerWrapper::from).toList();
  }
}
