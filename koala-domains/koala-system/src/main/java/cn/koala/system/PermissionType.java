package cn.koala.system;

import cn.koala.mybatis.EnhancedEnum;
import lombok.Getter;

/**
 * 权限类型
 *
 * @author Houtaroy
 */
@Getter
public enum PermissionType implements EnhancedEnum {
  MENU("菜单", 1),
  BUTTON("按钮", 2);

  private final String name;
  private final int value;

  PermissionType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
