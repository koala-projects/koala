package cn.koala.persist.support;

import cn.koala.persist.EntityListenerFactory;
import cn.koala.persist.SystemEntityListener;
import jakarta.persistence.EntityListeners;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 基于Spring Bean的实体监听器工厂
 * <p>
 * 内部整合了{@link ApplicationContext}用来获取实体监听器的Bean
 *
 * @author Houtaroy
 */
public class SpringBeanEntityListenerFactory implements EntityListenerFactory, ApplicationContextAware {

  @Setter
  private ApplicationContext applicationContext;

  @Override
  public List<Object> getEntityListeners(Class<?> entityClass) {
    List<Object> listeners = getEntityListeners(entityClass.getAnnotation(EntityListeners.class));
    List<SystemEntityListener> systemListeners = getSystemEntityListeners(entityClass);
    List<Object> result = new ArrayList<>(listeners.size() + systemListeners.size());
    result.addAll(systemListeners);
    result.addAll(listeners);
    return result;
  }

  private List<Object> getEntityListeners(EntityListeners annotation) {
    if (annotation == null) {
      return List.of();
    }
    List<Object> result = new ArrayList<>(annotation.value().length);
    for (Class<?> clazz : annotation.value()) {
      result.add(applicationContext.getBean(clazz));
    }
    return sort(result);
  }

  private List<SystemEntityListener> getSystemEntityListeners(Class<?> entityClass) {
    Collection<SystemEntityListener> listeners = applicationContext.getBeansOfType(SystemEntityListener.class).values();
    List<SystemEntityListener> result = listeners.stream().filter(listener -> listener.support(entityClass)).toList();
    return sort(result);
  }

  private <T> List<T> sort(List<T> listeners) {
    List<T> orders = new ArrayList<>(listeners.size());
    List<T> others = new ArrayList<>(listeners.size());
    listeners.forEach(listener -> (isOrderly(listener) ? orders : others).add(listener));

    List<T> result = new ArrayList<>(listeners.size());
    AnnotationAwareOrderComparator.sort(orders);
    result.addAll(orders);
    result.addAll(others);

    return result;
  }

  private boolean isOrderly(Object listener) {
    return listener.getClass().isAnnotationPresent(Order.class) || Ordered.class.isAssignableFrom(listener.getClass());
  }
}
