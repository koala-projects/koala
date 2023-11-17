package cn.koala.codegen.name;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 简易复数服务类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SimplePluralService implements PluralService {

  private final List<PluralConverter> converters;

  @Override
  public String plural(String singular) {
    for (PluralConverter converter : converters) {
      if (converter.matches(singular)) {
        return converter.convert(singular);
      }
    }
    return singular;
  }
}
