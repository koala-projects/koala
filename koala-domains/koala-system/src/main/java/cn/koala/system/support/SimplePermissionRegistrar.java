package cn.koala.system.support;

import cn.koala.system.Permission;
import cn.koala.system.PermissionRegistrar;
import lombok.Getter;
import org.springframework.util.Assert;

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

  public SimplePermissionRegistrar(String code, String name, Integer sortIndex, Map<String, String> children) {
    this.code = code;
    this.order = sortIndex;
    this.permissions = PermissionFactory.create(code, name, sortIndex.longValue(), children);
  }

  public void append(String code, String name) {
    this.append(code, name, this.obtainNextCrudSortIndex());
  }

  public void append(String code, String name, Long sortIndex) {
    Assert.isTrue(this.getPermissions().size() <= MAX, "权限注册器最多支持%d个权限".formatted(MAX));
    this.getPermissions().add(PermissionFactory.create(code, name, sortIndex, this.getPermissions().get(0)));
  }

  private Long obtainNextCrudSortIndex() {
    return this.getPermissions().get(this.getPermissions().size() - 1).getSortIndex() + 1;
  }
}
