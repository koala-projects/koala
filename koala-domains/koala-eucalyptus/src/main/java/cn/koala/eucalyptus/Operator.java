package cn.koala.eucalyptus;

import cn.koala.enhancement.ValueNameEnum;

/**
 * @author Houtaroy
 */
public enum Operator implements ValueNameEnum {
  /**
   * 等于
   */
  EQ(1, "等于"),
  /**
   * 不等于
   */
  NE(2, "不等于"),
  /**
   * 大于
   */
  GT(3, "大于"),
  /**
   * 大于等于
   */
  GE(4, "大于等于"),
  /**
   * 小于
   */
  LT(5, "小于"),
  /**
   * 小于等于
   */
  LE(6, "小于等于"),
  /**
   * 包含...
   */
  INCLUDE(7, "包含..."),
  /**
   * 不包含...
   */
  NOT_INCLUDE(8, "不包含..."),
  /**
   * 以...开头
   */
  START_WITH(9, "以...开头"),
  /**
   * 以...结尾
   */
  END_WITH(10, "以...结尾"),
  /**
   * 不以...开头
   */
  NOT_START_WITH(11, "不以...开头"),
  /**
   * 不以...结尾
   */
  NOT_END_WITH(12, "不以...结尾"),
  /**
   * "在...中"
   */
  IN(13, "在...中"),
  /**
   * "不在...中"
   */
  NOT_IN(14, "不在...中"),
  /**
   * 为空
   */
  IS_NULL(15, "为空"),
  /**
   * 不为空
   */
  IS_NOT_NULL(16, "不为空");

  private final int value;
  private final String name;

  Operator(int value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * 根据值返回枚举
   *
   * @param value 枚举值
   * @return 枚举
   */
  public static Operator valueOf(int value) {
    return ValueNameEnum.valueOf(Operator.class, value);
  }
}
