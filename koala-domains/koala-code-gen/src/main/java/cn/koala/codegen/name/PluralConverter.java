package cn.koala.codegen.name;

/**
 * 复数转换器接口
 *
 * @author Houtaroy
 */
public interface PluralConverter {

  String convert(String source);

  boolean matches(String source);
}
