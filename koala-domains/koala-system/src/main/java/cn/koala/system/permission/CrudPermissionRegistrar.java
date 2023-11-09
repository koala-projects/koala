package cn.koala.system.permission;

import lombok.Getter;

/**
 * 增删改查权限注册登记器
 *
 * @author Houtaroy
 */
@Getter
public class CrudPermissionRegistrar extends SimplePermissionRegistrar {

  public CrudPermissionRegistrar(String code, String name, Integer sortIndex, Long parentId) {
    super(code, name, sortIndex, parentId, PermissionFactory.CRUD_MAPPING);
  }
}
