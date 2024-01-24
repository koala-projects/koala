package cn.koala.system.permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统管理权限注册登记器
 *
 * @author Houtaroy
 */
public class SystemPermissionRegistrar extends MultiCrudPermissionRegistrar {

  private static final List<CrudPermission> CRUDS;

  static {
    CRUDS = new ArrayList<>();
    CRUDS.add(CrudPermission.of("user", "用户管理", 1100L));
    CRUDS.add(CrudPermission.of("role", "角色管理", 1200L));
    CRUDS.add(CrudPermission.of("permission", "权限管理", 1300L));
    CRUDS.add(CrudPermission.of("department", "部门管理", 1400L));
    CRUDS.add(CrudPermission.of("duty", "岗位管理", 1500L));
    CRUDS.add(CrudPermission.of("dictionary", "字典管理", 1700L));
    CRUDS.add(CrudPermission.of("setting", "设置管理", 1900L));
  }

  public SystemPermissionRegistrar() {
    super("system", "系统管理", 1000, CRUDS);
  }
}
