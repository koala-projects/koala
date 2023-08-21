package cn.koala.system.support;

import cn.koala.system.Permission;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权限工厂
 * <p>
 * 需要注意如下两个内省逻辑:
 * <p>
 * 1. 默认使用排序属性order作为主键
 * <p>
 * 2. 如果权限名称以"管理"结尾, 在创建下级时会自动去除"管理"两个字
 *
 * @author Houtaroy
 */
public class PermissionFactory {

  public static final String CODE_TEMPLATE = "%s.%s";

  public static final String NAME_TEMPLATE = "%s%s";

  public static final String PARENT_NAME_SUFFIX = "管理";

  public static List<Permission> create(String code, String name, Long sortIndex, Map<String, String> children) {
    List<Permission> result = new ArrayList<>(children.size() + 1);

    Permission parent = PermissionEntity.builder()
      .id(sortIndex)
      .code(code)
      .name(name)
      .sortIndex(sortIndex)
      .build();
    result.add(parent);

    long crudSortIndex = sortIndex + 1;
    for (Map.Entry<String, String> entry : children.entrySet()) {
      result.add(PermissionFactory.create(entry.getKey(), entry.getValue(), crudSortIndex, parent));
      crudSortIndex += 1;
    }

    return result;
  }

  public static Permission create(String code, String name, Long sortIndex, Permission parent) {
    Assert.notNull(parent, "上级权限不能为空");
    String parentName = PermissionFactory.obtainParentName(parent.getName());
    return PermissionEntity.builder()
      .id(sortIndex)
      .code(PermissionFactory.CODE_TEMPLATE.formatted(parent.getCode(), code))
      .name(PermissionFactory.NAME_TEMPLATE.formatted(name, parentName))
      .sortIndex(sortIndex)
      .parentId(parent.getId())
      .build();
  }

  private static String obtainParentName(String parentName) {
    return parentName.endsWith(PermissionFactory.PARENT_NAME_SUFFIX) ?
      parentName.substring(0, parentName.length() - PermissionFactory.PARENT_NAME_SUFFIX.length()) : parentName;
  }
}
