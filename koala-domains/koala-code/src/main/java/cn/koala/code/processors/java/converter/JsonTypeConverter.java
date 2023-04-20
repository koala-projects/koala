package cn.koala.code.processors.java.converter;

import cn.koala.toolkit.converter.DefaultableMapConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Java类型转换器
 *
 * @author Houtaroy
 */
public class JsonTypeConverter extends DefaultableMapConverter<Integer, String> {

  private static final Map<Integer, String> MAP;

  static {
    MAP = new HashMap<>(11);
    MAP.put(-6, "integer");
    MAP.put(5, "integer");
    MAP.put(4, "integer");
    MAP.put(-5, "integer");
    MAP.put(6, "number");
    MAP.put(8, "number");
    MAP.put(2, "number");
    MAP.put(3, "number");
    MAP.put(91, "date-time");
    MAP.put(92, "time");
    MAP.put(93, "date-time");
    MAP.put(16, "boolean");
  }

  public JsonTypeConverter() {
    super(MAP, "string");
  }
}
