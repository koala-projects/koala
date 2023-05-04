package cn.koala.persist.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.OrderComparator;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认实体监听器注册器
 *
 * @author Houtaroy
 */
@Deprecated
@Slf4j
@RequiredArgsConstructor
public class DefaultEntityListenerRegistry implements EntityListenerRegistry {

  private final MultiValueMap<Class<?>, EntityListener<?>> map;

  public DefaultEntityListenerRegistry(List<EntityListener<?>> listeners) {
    this.map = new LinkedMultiValueMap<>();
    listeners.forEach(listener -> {
      if (listener.getEntityClass() == null) {
        LOGGER.warn("实体监听器[name={}]没有指定监听类型, 将被忽略", listener.getClass().getSimpleName());
      } else {
        map.add(listener.getEntityClass(), listener);
      }
    });
  }

  @Override
  public void register(EntityListenerSupport<?> support) {
    if (support.getEntityClass() != null) {
      doRegister(support, support.getEntityClass());
    }
  }

  @SuppressWarnings("unchecked")
  protected <T> void doRegister(EntityListenerSupport<?> support, Class<T> supportClass) {
    List<EntityListener<?>> listeners = getListeners(supportClass);
    ((EntityListenerSupport<T>) support).registerListeners(
      listeners.stream().map(listener -> (EntityListener<? super T>) listener).collect(Collectors.toList())
    );
  }

  protected List<EntityListener<?>> getListeners(Class<?> supportClass) {
    List<EntityListener<?>> result = new ArrayList<>();
    map.forEach((clazz, listeners) -> {
      if (clazz.isAssignableFrom(supportClass)) {
        result.addAll(listeners);
      }
    });
    return result.stream().sorted(OrderComparator.INSTANCE).collect(Collectors.toList());
  }
}
