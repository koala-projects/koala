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
  protected final Map<S, T> statics;

  @Override
  public T convert(S source) {
    return statics.get(source);
  }

  @Override
  public boolean support(S source) {
    return statics.containsKey(source);
  }
}
