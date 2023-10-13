package cn.koala.persist;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 实体监听器工厂
 *
 * @author Houtaroy
 */
public interface EntityListenerMethodFactory {

  List<Method> getEntityListenerMethods(Object listener, Class<? extends Annotation> annotation);
}
