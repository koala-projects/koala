package cn.koala.mybatis.listener;

import cn.koala.util.BeanUtils;
import jakarta.persistence.EntityListeners;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于Spring Bean的实体监听器工厂
 * <p>
 * 内部整合了{@link ApplicationContext}用来获取实体监听器的Bean
 *
 * @author Houtaroy
 */
public class BeanEntityListenerFactory implements EntityListenerFactory, ApplicationContextAware {

  private final Map<Class<?>, List<Object>> listeners = new ConcurrentHashMap<>();
  private final Map<String, List<EntityListenerMethod>> methods = new ConcurrentHashMap<>();

  @Setter
  private ApplicationContext applicationContext;

  @Override
  public List<Object> getEntityListeners(Class<?> entityClass) {
    return listeners.computeIfAbsent(entityClass, (clazz) -> {
      if (clazz == null) {
        return List.of();
      }
      List<Object> listeners = getEntityListeners(clazz.getAnnotation(EntityListeners.class));
      List<GlobalEntityListener> globalEntityListeners = getGlobalEntityListeners(clazz);
      List<Object> result = new ArrayList<>(listeners.size() + globalEntityListeners.size());
      result.addAll(listeners);
      result.addAll(globalEntityListeners);
      BeanUtils.sort(result);
      return result;
    });
  }

  private List<Object> getEntityListeners(EntityListeners annotation) {
    if (annotation == null) {
      return List.of();
    }
    List<Object> result = new ArrayList<>(annotation.value().length);
    for (Class<?> clazz : annotation.value()) {
      result.add(applicationContext.getBean(clazz));
    }
    return result;
  }

  private List<GlobalEntityListener> getGlobalEntityListeners(Class<?> entityClass) {
    return applicationContext.getBeansOfType(GlobalEntityListener.class).values().stream()
      .filter(listener -> listener.support(entityClass))
      .toList();
  }

  @Override
  public List<EntityListenerMethod> getEntityListenerMethods(Class<?> entityClass,
                                                             Class<? extends Annotation> jpaAnnotation) {

    if (entityClass == null || jpaAnnotation == null) {
      return List.of();
    }
    String cacheKey = obtainEntityListenerMethodCacheKey(entityClass, jpaAnnotation);
    return methods.computeIfAbsent(cacheKey, (clazz) ->
      getEntityListeners(entityClass).stream()
        .flatMap(listener -> getEntityListenerMethods(listener, jpaAnnotation).stream())
        .toList()
    );
  }

  private String obtainEntityListenerMethodCacheKey(Class<?> entityClass, Class<? extends Annotation> jpaAnnotation) {
    return entityClass.getName() + "#" + jpaAnnotation.getName();
  }

  private List<EntityListenerMethod> getEntityListenerMethods(Object listener,
                                                              Class<? extends Annotation> jpaAnnotation) {

    List<Method> methods = Arrays.stream(listener.getClass().getMethods())
      .filter(method -> method.isAnnotationPresent(jpaAnnotation))
      .toList();
    List<Method> sortedMethods = BeanUtils.sort(methods);
    return sortedMethods.stream().map(method -> EntityListenerMethod.of(listener, method)).toList();
  }
}
