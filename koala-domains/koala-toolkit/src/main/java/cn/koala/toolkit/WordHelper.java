package cn.koala.toolkit;

import cn.koala.toolkit.converter.AppendConverter;
import cn.koala.toolkit.converter.Converter;
import cn.koala.toolkit.converter.ReplaceConverter;
import cn.koala.toolkit.converter.StaticConverter;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 单词帮助类
 *
 * @author Houtaroy
 */
@Deprecated
public abstract class WordHelper {
  private static final List<Converter<String, String>> PLURAL_CONVERTERS;

  static {
    PLURAL_CONVERTERS = new ArrayList<>();
    Map<String, String> words = Maps.newHashMap();
    words.put("child", "children");
    words.put("Chinese", "Chinese");
    words.put("deer", "deer");
    words.put("foot", "feet");
    words.put("goose", "geese");
    words.put("Japanese", "Japanese");
    words.put("man", "men");
    words.put("mouse", "mice");
    words.put("person", "people");
    words.put("sheep", "sheep");
    words.put("tooth", "teeth");
    words.put("woman", "women");
    PLURAL_CONVERTERS.add(new StaticConverter<>(words));
    PLURAL_CONVERTERS.add(new ReplaceConverter(Pattern.compile("(f|fe)$", Pattern.CASE_INSENSITIVE), "ves"));
    PLURAL_CONVERTERS.add(new ReplaceConverter(Pattern.compile("([^aeiou])y$", Pattern.CASE_INSENSITIVE), "$1ies"));
    PLURAL_CONVERTERS.add(new AppendConverter(Pattern.compile("(s|sh|ch|x)$", Pattern.CASE_INSENSITIVE), "es"));
    PLURAL_CONVERTERS.add(new AppendConverter(Pattern.compile("$", Pattern.CASE_INSENSITIVE), "s"));
  }

  public static String plural(String word) {
    for (Converter<String, String> pluralConverter : PLURAL_CONVERTERS) {
      if (pluralConverter.support(word)) {
        return pluralConverter.convert(word);
      }
    }
    return word;
  }
}
