package cn.koala.persist.autoconfigure;

import cn.koala.persist.CrudService;
import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.EntityListenerSelector;
import cn.koala.persist.listener.EntityListenerSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.OrderComparator;

import java.util.List;

/**
 * 增删改查服务的BeanPostProcessor
 * <p>
 * 用于注册实体监听器
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class CrudServicePostProcessor implements BeanPostProcessor {
  private final List<EntityListener> listeners;

  @Override
  public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
    if (bean instanceof CrudService<?, ?> service) {
      registerListeners(service, listeners);
    }
    return bean;
  }

  protected void registerListeners(CrudService<?, ?> service, List<EntityListener> listeners) {
    if (service instanceof EntityListenerSupport support) {
      support.registerListeners(listeners.stream()
        .filter(listener -> isRegistrable(listener, support))
        .sorted(OrderComparator.INSTANCE)
        .toList());
    }
  }

  protected boolean isRegistrable(EntityListener listener, EntityListenerSupport support) {
    if (listener instanceof EntityListenerSelector selector) {
      return support.getEntityType().filter(selector::match).isPresent();
    }
    return true;
  }
}
