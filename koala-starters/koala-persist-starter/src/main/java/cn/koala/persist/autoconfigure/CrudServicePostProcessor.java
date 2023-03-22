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
      listeners.forEach(listener -> registerListener(service, listener));
    }
    return bean;
  }

  protected void registerListener(CrudService<?, ?> service, EntityListener listener) {
    if (service instanceof EntityListenerSupport support) {
      if (listener instanceof EntityListenerSelector selector) {
        support.getEntityType().filter(selector::match).ifPresent(type -> support.registerListener(listener));
      } else {
        support.registerListener(listener);
      }
    }
  }
}
