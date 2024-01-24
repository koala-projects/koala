package cn.koala.system.permission;

import cn.koala.system.domain.Permission;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 复合增删改查权限注册登记器
 *
 * @author Houtaroy
 */
@Getter
public class MultiCrudPermissionRegistrar implements PermissionRegistrar {

  private final static int CRUD_SORT_INDEX_STEP = 100;

  private final String code;

  private final int order;

  private final List<Permission> permissions;

  public MultiCrudPermissionRegistrar(String code, String name, Integer sortIndex, List<CrudPermission> crudPermissions) {
    this.code = code;
    this.order = sortIndex;
    this.permissions = new ArrayList<>(crudPermissions.size() * (PermissionFactory.CRUD_MAPPING.size() + 1));
    this.permissions.add(PermissionFactory.of(null, code, name, sortIndex.longValue()));
    crudPermissions.forEach(permission ->
      this.permissions.addAll(PermissionFactory.ofCrud(
        sortIndex.longValue(),
        permission.getCode(),
        permission.getName(),
        permission.getSortIndex()
      ))
    );
  }
}
