package cn.houtaroy.koala.domain.models;

/**
 * @author Houtaroy
 */
public enum YesNo implements ValueNameEnum {

  /**
   * 是
   */
  YES(1, "是"),
  /**
   * 否
   */
  NO(0, "否");

  private final int value;
  private final String name;

  YesNo(int value, String name) {
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
  public static YesNo valueOf(int value) {
    return ValueNameEnum.valueOf(YesNo.class, value);
  }
}
