package cn.koala.persist;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解解析器
 *
 * @author Houtaroy
 */
public interface AnnotationResolver {

  Class<? extends Annotation> resolveAnnotation(Method method);
}
