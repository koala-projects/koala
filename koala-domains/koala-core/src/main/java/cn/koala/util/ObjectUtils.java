package cn.koala.util;

import java.util.Arrays;

/**
 * 对象工具类
 *
 * @author Houtaroy
 */
public abstract class ObjectUtils extends org.springframework.util.ObjectUtils {

  public static <T> T getFirst(Object[] objects, Class<T> clazz) {
    return Arrays.stream(objects)
      .filter(param -> clazz.isAssignableFrom(param.getClass()))
      .findFirst()
      .map(clazz::cast)
      .orElse(null);
  }
}
