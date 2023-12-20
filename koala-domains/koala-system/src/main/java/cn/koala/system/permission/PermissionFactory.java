package cn.koala.system.permission;

import cn.koala.data.domain.YesNo;
import cn.koala.system.domain.Permission;
import cn.koala.system.domain.PermissionEntity;
import cn.koala.system.util.SystemConstants;
import cn.koala.util.LocalDateTimeUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

  public static final Map<String, String> CRUD_MAPPING = new LinkedHashMap<>(4);

  static {
    CRUD_MAPPING.put("read", "读取");
    CRUD_MAPPING.put("create", "创建");
    CRUD_MAPPING.put("update", "更新");
    CRUD_MAPPING.put("delete", "删除");
  }

  public static List<Permission> ofCrud(Long parentId, String code, String name, Long sortIndex) {
    List<Permission> result = new ArrayList<>(CRUD_MAPPING.size() + 1);
    Permission parent = of(parentId, code, name, sortIndex);
    result.add(parent);
    result.addAll(ofChildren(parent, CRUD_MAPPING));
    return result;
  }

  public static List<Permission> ofChildren(Permission parent, Map<String, String> codeAndNames) {
    Assert.notNull(parent, "上级权限不能为空");
    long currentSortIndex = parent.getSortIndex() + 1;
    List<Permission> result = new ArrayList<>(codeAndNames.size());
    for (String code : codeAndNames.keySet()) {
      result.add(ofChild(parent, code, codeAndNames.get(code), currentSortIndex));
      currentSortIndex += 1;
    }
    return result;
  }

  public static Permission ofChild(Permission parent, String code, String name, Long sortIndex) {
    String domainName = obtainDomainName(parent.getName());
    String actualCode = CODE_TEMPLATE.formatted(parent.getCode(), code);
    String actualName = NAME_TEMPLATE.formatted(name, domainName);
    return of(parent.getId(), actualCode, actualName, sortIndex);
  }

  private static String obtainDomainName(String parentName) {
    return parentName.endsWith(PermissionFactory.PARENT_NAME_SUFFIX) ?
      parentName.substring(0, parentName.length() - PermissionFactory.PARENT_NAME_SUFFIX.length()) : parentName;
  }

  public static Permission of(Long parentId, String code, String name, Long sortIndex) {
    return PermissionEntity.builder()
      .id(sortIndex)
      .parentId(parentId)
      .code(code)
      .name(name)
      .sortIndex(sortIndex)
      .enabled(YesNo.YES)
      .systemic(YesNo.NO)
      .deleted(YesNo.NO)
      .createdBy(SystemConstants.ADMIN_ID)
      .createdDate(LocalDateTimeUtils.toDate())
      .build();
  }
}
