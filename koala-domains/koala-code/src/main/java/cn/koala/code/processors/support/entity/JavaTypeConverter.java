package cn.koala.code.processors.support.entity;

import cn.koala.toolkit.converter.DefaultableMapConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Java类型转换器
 *
 * @author Houtaroy
 */
public class JavaTypeConverter extends DefaultableMapConverter<Integer, String> {

  public static final Map<Integer, String> Map;

  static {
    Map = new HashMap<>(11);
    Map.put(-6, JavaType.Integer.getName());
    Map.put(5, JavaType.Integer.getName());
    Map.put(4, JavaType.Integer.getName());
    Map.put(-5, JavaType.Long.getName());
    Map.put(6, JavaType.Float.getName());
    Map.put(8, JavaType.Double.getName());
    Map.put(2, JavaType.Double.getName());
    Map.put(3, JavaType.Double.getName());
    Map.put(91, JavaType.Date.getName());
    Map.put(92, JavaType.Time.getName());
    Map.put(93, JavaType.Date.getName());
    Map.put(16, JavaType.Boolean.getName());
  }

  public JavaTypeConverter() {
    super(Map, JavaType.String.getName());
  }
}
