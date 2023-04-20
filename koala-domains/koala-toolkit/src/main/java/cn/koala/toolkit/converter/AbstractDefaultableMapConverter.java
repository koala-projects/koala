package cn.koala.toolkit.converter;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 支持默认值的Map转换器
 *
 * @author Houtaroy
 */
@Data
@RequiredArgsConstructor
public abstract class AbstractDefaultableMapConverter<S, T> implements Converter<S, T> {
  private final Map<S, T> map;
  private final T defaultValue;

  @Override
  public T convert(S source) {
    return map.getOrDefault(source, defaultValue);
  }
}
