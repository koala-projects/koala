package cn.koala.toolkit;

import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    return typeToken.resolveType(genericClass.getTypeParameters()[index]).getRawType();
  }

  public static List<Method> getMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
    return Arrays.stream(clazz.getMethods())
      .filter(method -> method.isAnnotationPresent(annotation))
      .toList();
  }
}
