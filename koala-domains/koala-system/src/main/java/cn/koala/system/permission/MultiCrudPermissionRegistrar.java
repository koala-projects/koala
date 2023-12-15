package cn.koala.system.permission;

import cn.koala.system.domain.Permission;
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
    this.permissions.add(PermissionFactory.of(null, code, name, startSortIndex.longValue()));
    long currentSortIndex = startSortIndex + CRUD_SORT_INDEX_STEP;
    for (String crudCode : cruds.keySet()) {
      this.permissions.addAll(
        PermissionFactory.ofCrud(startSortIndex.longValue(), crudCode, cruds.get(crudCode), currentSortIndex)
      );
      currentSortIndex += CRUD_SORT_INDEX_STEP;
    }
  }
}
