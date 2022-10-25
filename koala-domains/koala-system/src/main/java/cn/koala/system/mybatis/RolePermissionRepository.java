package cn.koala.system.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限关系存储库
 *
 * @author Houtaroy
 */
public interface RolePermissionRepository {
  /**
   * 根据角色id查询权限id列表
   *
   * @param roleId 角色id
   * @return 权限id列表
   */
  List<String> findAllPermissionIdByRoleId(@Param("roleId") String roleId);

  /**
   * 新增角色权限关系
   *
   * @param roleId        角色id
   * @param permissionIds 权限id列表
   */
  void add(@Param("roleId") String roleId, @Param("permissionIds") List<String> permissionIds);

  /**
   * 根据角色id删除角色权限关系
   *
   * @param roleId 角色id
   */
  void deleteByRoleId(@Param("roleId") String roleId);
}
