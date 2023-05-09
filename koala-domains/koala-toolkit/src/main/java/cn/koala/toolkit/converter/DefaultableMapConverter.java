package cn.koala.toolkit.converter;

import java.util.Map;

/**
 * 支持默认值的Map转换器
 *
 * @author Houtaroy
 */
public class DefaultableMapConverter<S, T> extends MapConverter<S, T> {
  private final T defaultValue;

  public DefaultableMapConverter(Map<S, T> data, T defaultValue) {
    super(data);
    this.defaultValue = defaultValue;
  }

  @Override
  public T convert(S source) {
    return map.getOrDefault(source, defaultValue);
  }

  @Override
  public boolean support(S source) {
    return true;
  }
}
