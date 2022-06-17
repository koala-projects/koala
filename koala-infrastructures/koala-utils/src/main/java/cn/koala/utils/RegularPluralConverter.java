package cn.koala.utils;

import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则单词复数转换器
 * 利用正则表达式判断是否符合转换要求
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class RegularPluralConverter implements PluralConverter {
  protected final String expression;
  protected final String replacement;

  @Override
  public boolean isSupported(String singular) {
    return getMatcher(singular).find();
  }

  @Override
  public String convert(String singular) {
    Matcher matcher = getMatcher(singular);
    if (matcher.find()) {
      return matcher.replaceAll(replacement);
    }
    return singular;
  }

  /**
   * 获取匹配器
   *
   * @param singular 单数单词
   * @return 匹配器
   */
  public Matcher getMatcher(String singular) {
    return Pattern.compile(expression, Pattern.CASE_INSENSITIVE).matcher(singular);
  }
}
