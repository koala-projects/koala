package cn.koala.datamodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Houtaroy
 */
public abstract class PropertyTypeHelper {
  private static final Map<String, Function<String, Object>> PARSERS;

  static {
    PARSERS = new HashMap<>();
    PARSERS.put("Integer", Integer::parseInt);
    PARSERS.put("Long", Long::parseLong);
    PARSERS.put("Float", Float::parseFloat);
    PARSERS.put("Double", Double::parseDouble);
  }

  /**
   * 解析
   *
   * @param type    属性类型
   * @param content 数据元内容
   * @return 数据
   */
  public static Object parse(String type, String content) {
    return Optional.ofNullable(PARSERS.get(type))
      .map(parser -> parser.apply(content))
      .orElse(content);
  }

  /**
   * 新增解析器
   *
   * @param type   属性类型
   * @param parser 解析器
   */
  public static void addParser(String type, Function<String, Object> parser) {
    PARSERS.put(type, parser);
  }
}
