package cn.koala.toolkit.converter;

import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 静态转换器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class StaticConverter<S, T> implements Converter<S, T> {
  protected final Map<S, T> data;

  @Override
  public T convert(S source) {
    return data.get(source);
  }

  @Override
  public boolean isSupported(S source) {
    return data.containsKey(source);
  }
}
