package cn.koala.persist;

import cn.koala.toolkit.ReflectionHelper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Method;

/**
 * 实体监听器方法
 *
 * @author Houtaroy
 */
@Getter
@SuperBuilder(toBuilder = true)
public class EntityListenerMethod {

  private final Object listener;
  private final Method original;

  public Object invoke(Object... args) {
    return ReflectionHelper.invokeMethod(original, listener, args);
  }

  public static EntityListenerMethod of(Object listener, Method method) {
    return EntityListenerMethod.builder()
      .listener(listener)
      .original(method)
      .build();
  }
}
