package cn.koala.toolkit;

import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 类帮助类
 *
 * @author Houtaroy
 */
public abstract class ClassHelper {

  @Nullable
  public static List<Class<?>> getGenericClasses(Class<?> objectClass, Class<?> genericClass) {
    if (!genericClass.isAssignableFrom(objectClass)) {
      return null;
    }
    if (ArrayUtils.isEmpty(genericClass.getTypeParameters())) {
      return null;
    }
    TypeToken<?> typeToken = TypeToken.of(objectClass);
    List<Class<?>> result = new ArrayList<>(genericClass.getTypeParameters().length);
    Arrays.stream(genericClass.getTypeParameters())
      .map(typeToken::resolveType)
      .map(TypeToken::getType)
      .forEach(type -> result.add((Class<?>) type));
    return result;
  }

  public static Class<?> getGenericClass(Class<?> objectClass, Class<?> genericClass) {
    return getGenericClass(objectClass, genericClass, 0);
  }

  public static Class<?> getGenericClass(Class<?> objectClass, Class<?> genericClass, int index) {
    if (!genericClass.isAssignableFrom(objectClass)) {
      return null;
    }
    if (ArrayUtils.isEmpty(genericClass.getTypeParameters())) {
      return null;
    }
    if (genericClass.getTypeParameters().length < index) {
      return null;
    }
    TypeToken<?> typeToken = TypeToken.of(objectClass);
    return (Class<?>) typeToken.resolveType(genericClass.getTypeParameters()[index]).getType();
  }

  @Deprecated
  public static Class<?> getSuperGenericClass(Object object, Class<?> superClass) {
    return getSuperGenericClass(object.getClass(), superClass, 0);
  }


  @Deprecated
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
