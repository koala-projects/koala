package cn.koala.code.processors;

import cn.koala.toolkit.converter.StaticConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Java类型转换器
 *
 * @author Houtaroy
 */
public class JsonTypeConverter extends StaticConverter<Integer, String> {

  public static final Map<Integer, String> DATA;

  static {
    DATA = new HashMap<>(11);
    DATA.put(-6, "integer");
    DATA.put(5, "integer");
    DATA.put(4, "integer");
    DATA.put(-5, "integer");
    DATA.put(6, "number");
    DATA.put(8, "number");
    DATA.put(2, "number");
    DATA.put(3, "number");
    DATA.put(91, "date-time");
    DATA.put(92, "time");
    DATA.put(93, "date-time");
    DATA.put(16, "boolean");
  }

  public JsonTypeConverter() {
    super(DATA);
  }

  @Override
  public String convert(Integer source) {
    return data.getOrDefault(source, "string");
  }
}
