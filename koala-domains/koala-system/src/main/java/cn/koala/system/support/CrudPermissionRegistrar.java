package cn.koala.system.support;

import lombok.Getter;

import java.util.Map;

/**
 * 增删改查权限注册登记器
 *
 * @author Houtaroy
 */
@Getter
public class CrudPermissionRegistrar extends SimplePermissionRegistrar {

  public static final Map<String, String> CRUD_MAPPING = Map.of(
    "read", "读取",
    "create", "创建",
    "update", "更新",
    "delete", "删除"
  );

  public CrudPermissionRegistrar(String code, String name, Integer sortIndex) {
    super(code, name, sortIndex, CrudPermissionRegistrar.CRUD_MAPPING);
  }

}
