package cn.koala.toolkit.word;

import cn.koala.toolkit.converter.Converter;
import cn.koala.toolkit.converter.MapConverter;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 单词帮助类
 *
 * @author Houtaroy
 */
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
    PLURAL_CONVERTERS.add(new MapConverter<>(words));
    PLURAL_CONVERTERS.add(new ReplaceConverter("(f|fe)$", "ves"));
    PLURAL_CONVERTERS.add(new ReplaceConverter("([^aeiou])y$", "$1ies"));
    PLURAL_CONVERTERS.add(new AppendConverter("([s|sh|ch|x])$", "es"));
    PLURAL_CONVERTERS.add(new AppendConverter("$", "s"));
  }

  public static String plural(String word) {
    for (Converter<String, String> pluralConverter : PLURAL_CONVERTERS) {
      if (pluralConverter.isSupported(word)) {
        return pluralConverter.convert(word);
      }
    }
    return word;
  }
}
