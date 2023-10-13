package cn.koala.persist.support;

import cn.koala.persist.AnnotationResolver;
import cn.koala.persist.EntityListenerMethodFactory;
import cn.koala.persist.MethodEntityListenerMethodFactory;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 简易方法实体监听器方法工厂
 * <p>
 * 内部整合了{@link AnnotationResolver}和{@link EntityListenerMethodFactory}
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SimpleMethodEntityListenerMethodFactory implements MethodEntityListenerMethodFactory {

  private final AnnotationResolver annotationResolver;

  private final EntityListenerMethodFactory entityListenerMethodFactory;

  @Override
  public List<Method> getEntityListenerMethods(Object listener, Method method) {
    Class<? extends Annotation> methodJpaAnnotation = annotationResolver.resolveAnnotation(method);
    return entityListenerMethodFactory.getEntityListenerMethods(listener, methodJpaAnnotation);
  }
}
