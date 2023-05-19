package cn.koala.persist.listener.support;

import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.EntityListenerRegistry;
import cn.koala.persist.listener.EntityListenerWrapper;
import cn.koala.toolkit.registry.AbstractCacheableRegistry;

import java.util.List;

/**
 * 默认实体监听器注册表
 *
 * @author Houtaroy
 */
public class DefaultEntityListenerRegistry extends AbstractCacheableRegistry<Class<?>, EntityListenerWrapper>
  implements EntityListenerRegistry {

  public DefaultEntityListenerRegistry(List<EntityListener> listeners) {
    super(listeners.stream().map(EntityListenerWrapper::from).toList());
  }

  @Override
  protected boolean matches(Class<?> key, EntityListenerWrapper value) {
    return value.getListener().support(key);
  }
}
