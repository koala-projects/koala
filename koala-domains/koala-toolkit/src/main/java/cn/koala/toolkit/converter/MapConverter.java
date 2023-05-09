package cn.koala.toolkit.converter;

import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 静态转换器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class MapConverter<S, T> implements Converter<S, T> {
  protected final Map<S, T> map;

  @Override
  public T convert(S source) {
    return map.get(source);
  }

  @Override
  public boolean support(S source) {
    return map.containsKey(source);
  }
}
