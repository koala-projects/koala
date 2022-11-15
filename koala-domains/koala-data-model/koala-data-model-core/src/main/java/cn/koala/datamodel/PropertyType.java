package cn.koala.datamodel;

import cn.koala.enhancement.ValueNameEnum;
import cn.koala.lang.time.StringCalendar;
import lombok.Getter;

import java.util.function.Function;

/**
 * 属性类型
 *
 * @author Houtaroy
 */
@Getter
public enum PropertyType implements ValueNameEnum {

  /**
   * 整型
   */
  INTEGER(1, "整型", Integer::parseInt),
  /**
   * 浮点型
   */
  FLOAT(2, "浮点型", Double::parseDouble),
  /**
   * 字符串
   */
  STRING(3, "字符串", (value) -> value),
  /**
   * 布尔
   */
  BOOLEAN(4, "布尔", Boolean::parseBoolean),
  /**
   * 日期
   */
  LOCAL_DATE_TIME(5, "日期", StringCalendar::parse);

  private final int value;
  private final String name;
  private final Function<String, Object> parser;

  PropertyType(int value, String name, Function<String, Object> parser) {
    this.value = value;
    this.name = name;
    this.parser = parser;
  }

  /**
   * 根据值返回枚举
   *
   * @param value 枚举值
   * @return 枚举
   */
  public static PropertyType valueOf(int value) {
    return ValueNameEnum.valueOf(PropertyType.class, value);
  }
}
