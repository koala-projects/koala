package cn.koala.lang;

import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 特殊单词复数转换器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class PluralSpecialConverter implements WordConverter {
  protected final Map<String, String> specials;

  @Override
  public boolean isSupported(String singular) {
    return specials.containsKey(singular);
  }

  @Override
  public String convert(String singular) {
    return specials.get(singular);
  }
}
