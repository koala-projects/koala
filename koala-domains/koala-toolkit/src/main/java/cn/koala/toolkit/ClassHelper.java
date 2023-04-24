package cn.koala.toolkit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * 类帮助类
 *
 * @author Houtaroy
 */
public abstract class ClassHelper {

  public static Class<?> getSuperGenericClass(Object object, Class<?> superClass) {
    return getSuperGenericClass(object.getClass(), superClass, 0);
  }


  public static Class<?> getSuperGenericClass(Class<?> clazz, Class<?> superClass, int index) {
    Type superType = clazz.getGenericSuperclass();
    if (!(superType instanceof ParameterizedType)) {
      return getSuperGenericClass((Class<?>) superType, superClass, index);
    }
    return (Class<?>) Optional.of(superType)
      .map(ParameterizedType.class::cast)
      .filter(type -> superClass.isAssignableFrom((Class<?>) type.getRawType()))
      .map(ParameterizedType::getActualTypeArguments)
      .filter(args -> args.length > index)
      .map(args -> args[index])
      .orElse(null);
  }
}
