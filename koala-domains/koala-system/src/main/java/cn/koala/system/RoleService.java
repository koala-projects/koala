package cn.koala.system;

import cn.koala.persistence.CrudService;

import java.util.List;

/**
 * 角色服务
 *
 * @author Houtaroy
 */
public interface RoleService extends CrudService<String, Role> {
  /**
   * 根据id获取权限id列表
   *
   * @param id 角色id
   * @return 权限id列表
   */
  List<String> permissionIds(String id);

  /**
   * 为角色授权
   *
   * @param id            角色id
   * @param permissionIds 权限id列表
   */
  void authorize(String id, List<String> permissionIds);
}
