package cn.koala.system.support;

import cn.koala.system.Permission;
import cn.koala.system.PermissionRegistrar;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 简易权限注册登记器
 *
 * @author Houtaroy
 */
@Getter
public class SimplePermissionRegistrar implements PermissionRegistrar {

  public static final int MAX = 99;

  private final String code;

  private final int order;

  protected final List<Permission> permissions;

  public SimplePermissionRegistrar(String code, String name, Integer sortIndex, Long parentId,
                                   Map<String, String> childrenCodeAndNames) {
    this.code = code;
    this.order = sortIndex;
    this.permissions = new ArrayList<>(childrenCodeAndNames.size() + 1);
    this.permissions.add(PermissionFactory.of(code, name, sortIndex.longValue(), parentId));
    this.addChildren(childrenCodeAndNames);
  }

  public void addChildren(Map<String, String> childrenCodeAndNames) {
    this.permissions.addAll(PermissionFactory.ofChildren(this.permissions.get(0), childrenCodeAndNames));
  }

  public void addChild(String code, String name) {
    this.permissions.add(
      PermissionFactory.ofChild(this.permissions.get(0), code, name, this.obtainCurrentSortIndex() + 1)
    );
  }

  private Long obtainCurrentSortIndex() {
    return this.permissions.get(this.permissions.size() - 1).getSortIndex();
  }
}
