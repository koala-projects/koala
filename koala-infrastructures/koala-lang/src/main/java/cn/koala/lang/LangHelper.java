package cn.koala.lang;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 语言帮助类
 *
 * @author Houtaroy
 */
public abstract class LangHelper {
  private static final List<WordConverter> PLURAL_CONVERTERS;

  static {
    PLURAL_CONVERTERS = new ArrayList<>();
    Map<String, String> specials = Maps.newHashMap();
    specials.put("child", "children");
    specials.put("Chinese", "Chinese");
    specials.put("deer", "deer");
    specials.put("foot", "feet");
    specials.put("goose", "geese");
    specials.put("Japanese", "Japanese");
    specials.put("man", "men");
    specials.put("mouse", "mice");
    specials.put("person", "people");
    specials.put("sheep", "sheep");
    specials.put("tooth", "teeth");
    specials.put("woman", "women");
    PLURAL_CONVERTERS.add(new PluralSpecialConverter(specials));
    PLURAL_CONVERTERS.add(new PluralRegularConverter("(f|fe)$", "ves"));
    PLURAL_CONVERTERS.add(new PluralRegularConverter("([^aeiou])y$", "$1ies"));
    PLURAL_CONVERTERS.add(new PluralAppendConverter("([s|sh|ch|x])$", "es"));
    PLURAL_CONVERTERS.add(new PluralAppendConverter("$", "s"));
  }

  /**
   * 转换为复数
   *
   * @param singular 单数单词
   * @return 复数单词
   */
  public static String plural(String singular) {
    for (WordConverter pluralConverter : PLURAL_CONVERTERS) {
      if (pluralConverter.isSupported(singular)) {
        return pluralConverter.convert(singular);
      }
    }
    return singular;
  }

  /**
   * 去除文章内容换行符
   * abc\n\r123 -> abc123
   *
   * @param content 文章内容
   * @return 去除换行符后的文章内容
   */
  public static String chomp(String content) {
    return content.replace("\n", "")
      .replace("\r", "")
      .replace("\t", "");
  }
}
