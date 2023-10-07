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

  private final static int CRUD_SORT_INDEX_STEP = 100;

  private final String code;

  private final int order;

  private final List<Permission> permissions;

  public MultiCrudPermissionRegistrar(String code, String name, Integer startSortIndex, Map<String, String> cruds) {
    this.code = code;
    this.order = startSortIndex;
    this.permissions = new ArrayList<>(cruds.size() * (PermissionFactory.CRUD_MAPPING.size() + 1));
    this.permissions.add(PermissionFactory.of(code, name, startSortIndex.longValue(), null));
    long currentSortIndex = startSortIndex + CRUD_SORT_INDEX_STEP;
    for (String crudCode : cruds.keySet()) {
      this.permissions.addAll(
        PermissionFactory.ofCrud(crudCode, cruds.get(crudCode), currentSortIndex, startSortIndex.longValue())
      );
      currentSortIndex += CRUD_SORT_INDEX_STEP;
    }
  }
}
