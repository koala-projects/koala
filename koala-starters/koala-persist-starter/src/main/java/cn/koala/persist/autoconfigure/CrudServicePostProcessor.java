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
import org.springframework.core.MethodParameter;

import java.util.List;
import java.util.Optional;

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
  private final static String ADD_METHOD_NAME = "add";

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
        getEntityType(service).filter(selector::match).ifPresent(type -> support.registerListener(listener));
      } else {
        support.registerListener(listener);
      }
    }
  }

  protected Optional<Class<?>> getEntityType(CrudService<?, ?> service) {
    try {
      return Optional.of(
        new MethodParameter(service.getClass().getDeclaredMethod(ADD_METHOD_NAME), 0).getParameterType()
      );
    } catch (NoSuchMethodException e) {
      LOGGER.error("服务类[%s]获取实体类型失败, 无法注册实体监听器".formatted(service.getClass().getName()), e);
      return Optional.empty();
    }
  }
}
