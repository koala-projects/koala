package cn.koala.system.support;

import cn.koala.system.Permission;
import cn.koala.system.PermissionRegistrar;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 复合增删改查权限注册登记器
 *
 * @author Houtaroy
 */
@Getter
public class MultiCrudPermissionRegistrar implements PermissionRegistrar {

  private final String code;

  private final int order;

  private final List<Permission> permissions;

  public MultiCrudPermissionRegistrar(String code, Map<String, String> cruds, int startSortIndex) {
    this.code = code;
    this.order = startSortIndex;
    this.permissions = new ArrayList<>(cruds.size() * (CrudPermissionRegistrar.CRUD_MAPPING.size() + 1));
    long currentSortIndex = startSortIndex;
    for (Map.Entry<String, String> entry : cruds.entrySet()) {
      this.permissions.addAll(PermissionFactory.create(entry.getKey(), entry.getValue(), currentSortIndex, CrudPermissionRegistrar.CRUD_MAPPING));
      currentSortIndex += SimplePermissionRegistrar.MAX + 1;
    }
  }
}
