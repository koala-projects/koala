package cn.koala.toolkit;

import java.util.Optional;
import java.util.function.Function;

/**
 * 对象帮助类
 *
 * @author Houtaroy
 */
public abstract class ObjectHelper {

  public static <T, R> R getOrDefault(T object, Function<T, R> supplier, R defaultValue) {
    return Optional.ofNullable(object)
      .map(supplier)
      .orElse(defaultValue);
  }
}
