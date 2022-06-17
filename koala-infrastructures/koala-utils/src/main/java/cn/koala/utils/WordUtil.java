package cn.koala.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词工具类
 *
 * @author Houtaroy
 */
public final class WordUtil {
  private static final List<PluralConverter> PLURAL_CONVERTERS;

  static {
    PLURAL_CONVERTERS = new ArrayList<>();
    PLURAL_CONVERTERS.add(new SpecifyPluralConverter("SpecifyPlural.txt"));
    PLURAL_CONVERTERS.add(new RegularPluralConverter("(f|fe)$", "ves"));
    PLURAL_CONVERTERS.add(new RegularPluralConverter("([^aeiou])y$", "$1ies"));
    PLURAL_CONVERTERS.add(new AppendPluralConverter("([s|sh|ch|x])$", "es"));
    PLURAL_CONVERTERS.add(new AppendPluralConverter("$", "s"));
  }

  private WordUtil() {
  }

  /**
   * 转换为复数
   *
   * @param singular 单数单词
   * @return 复数单词
   */
  public static String plural(String singular) {
    for (PluralConverter pluralConverter : PLURAL_CONVERTERS) {
      if (pluralConverter.isSupported(singular)) {
        return pluralConverter.convert(singular);
      }
    }
    return singular;
  }
}
