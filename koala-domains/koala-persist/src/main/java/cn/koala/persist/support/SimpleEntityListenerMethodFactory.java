package cn.koala.persist.support;

import cn.koala.persist.EntityListenerMethodFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 简单实体监听器方法工厂
 *
 * @author Houtaroy
 */
public class SimpleEntityListenerMethodFactory implements EntityListenerMethodFactory {

  @Override
  public List<Method> getEntityListenerMethods(Object listener, Class<? extends Annotation> annotation) {
    return Arrays.stream(listener.getClass().getMethods())
      .filter(method -> method.isAnnotationPresent(annotation))
      .toList();
  }
}
