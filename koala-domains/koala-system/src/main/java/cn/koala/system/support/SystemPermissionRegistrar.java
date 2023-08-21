package cn.koala.system.support;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统管理权限注册登记器
 *
 * @author Houtaroy
 */
public class SystemPermissionRegistrar extends MultiCrudPermissionRegistrar {

  private static final Map<String, String> CRUDS;

  static {
    CRUDS = new LinkedHashMap<>();
    CRUDS.put("user", "用户管理");
    CRUDS.put("role", "角色管理");
    CRUDS.put("permission", "权限管理");
    CRUDS.put("department", "部门管理");
    CRUDS.put("dictionary", "字典管理");
  }

  public SystemPermissionRegistrar() {
    super("system", CRUDS, 100);
  }
}
