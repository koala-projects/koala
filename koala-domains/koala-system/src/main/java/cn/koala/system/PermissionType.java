package cn.koala.system;

import cn.koala.enhancement.ValueNameEnum;

/**
 * @author Houtaroy
 */
public enum PermissionType implements ValueNameEnum {

  /**
   * 菜单
   */
  MENU(1, "菜单"),
  /**
   * 按钮
   */
  BUTTON(2, "按钮");

  private final int value;
  private final String name;

  PermissionType(int value, String name) {
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
  public static PermissionType valueOf(int value) {
    return ValueNameEnum.valueOf(PermissionType.class, value);
  }
}
