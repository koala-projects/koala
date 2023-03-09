package cn.koala.database;

import cn.koala.toolkit.converter.StaticConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Java类型转换器
 *
 * @author Houtaroy
 */
public class JavaTypeConverter extends StaticConverter<Integer, String> {

  public static final Map<Integer, String> DATA;

  static {
    DATA = new HashMap<>(11);
    DATA.put(-6, "Integer");
    DATA.put(5, "Integer");
    DATA.put(4, "Integer");
    DATA.put(-5, "Long");
    DATA.put(6, "Float");
    DATA.put(8, "Double");
    DATA.put(2, "Double");
    DATA.put(3, "Double");
    DATA.put(91, "Date");
    DATA.put(92, "Time");
    DATA.put(93, "Date");
    DATA.put(16, "Boolean");
  }

  public JavaTypeConverter() {
    super(DATA);
  }

  @Override
  public String convert(Integer source) {
    return data.getOrDefault(source, "String");
  }
}
