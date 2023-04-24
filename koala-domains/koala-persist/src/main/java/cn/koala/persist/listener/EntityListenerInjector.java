package cn.koala.persist.listener;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 实体监听器注入器
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class EntityListenerInjector implements BeanPostProcessor {

  private final EntityListenerRegistry registry;

  @Override
  public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
    if (bean instanceof EntityListenerSupport<?> support) {
      registry.register(support);
    }
    return bean;
  }
}
