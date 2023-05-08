package cn.koala.toolkit;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 反射帮助类
 *
 * @author Houtaroy
 */
public abstract class ReflectionHelper extends ReflectionUtils {

  public static boolean isMethodInvokable(Method method, Object[] args) {
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != args.length) {
      return false;
    }
    for (int i = 0; i < args.length; i++) {
      if (args[i] == null) {
        if (parameterTypes[i].isPrimitive()) {
          return false;
        }
      } else if (!parameterTypes[i].isAssignableFrom(args[i].getClass())) {
        return false;
      }
    }
    return true;
  }
}
