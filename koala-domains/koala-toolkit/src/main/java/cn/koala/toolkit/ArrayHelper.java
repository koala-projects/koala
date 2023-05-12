package cn.koala.toolkit;

import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * 数组帮助类
 *
 * @author Houtaroy
 */
public abstract class ArrayHelper {

  @Nullable
  public static <T> T get(Object[] objects, Class<T> clazz) {
    return Arrays.stream(objects)
      .filter(param -> clazz.isAssignableFrom(param.getClass()))
      .findFirst()
      .map(clazz::cast)
      .orElse(null);
  }
}
