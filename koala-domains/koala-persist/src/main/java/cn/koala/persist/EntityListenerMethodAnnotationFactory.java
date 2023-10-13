package cn.koala.persist;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public interface EntityListenerMethodAnnotationFactory {

  Class<? extends Annotation> getEntityListenerMethodPreAnnotation(Method method);

  Class<? extends Annotation> getEntityListenerMethodPostAnnotation(Method method);
}
