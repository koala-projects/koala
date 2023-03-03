package cn.koala.toolkit.converter;

/**
 * 转换器接口
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface Converter<S, T> {
  T convert(S source);

  default boolean isSupported(S source) {
    return true;
  }
}
