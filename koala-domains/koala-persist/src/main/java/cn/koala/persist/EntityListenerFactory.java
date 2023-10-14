package cn.koala.persist;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 实体监听器工厂
 *
 * @author Houtaroy
 */
public interface EntityListenerFactory {

  List<Object> getEntityListeners(Class<?> entityClass);

  List<EntityListenerMethod> getEntityListenerMethods(Class<?> entityClass, Class<? extends Annotation> jpaAnnotation);
}
