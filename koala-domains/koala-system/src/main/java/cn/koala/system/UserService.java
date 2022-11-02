package cn.koala.system;

import cn.koala.persistence.CrudService;

import java.util.List;

/**
 * 用户服务
 *
 * @author Houtaroy
 */
public interface UserService extends CrudService<String, User> {
  /**
   * 获取用户部门ID列表
   *
   * @param id 用户ID
   * @return 部门ID列表
   */
  List<String> listDepartmentIds(String id);

  /**
   * 设置部门ID列表
   *
   * @param id            用户ID
   * @param departmentIds 部门ID列表
   */
  void setDepartmentIds(String id, List<String> departmentIds);

  /**
   * 根据id获取角色id列表
   *
   * @param id 用户id
   * @return 角色id列表
   */
  List<String> listRoleIds(String id);

  /**
   * 设置用户角色
   *
   * @param id      用户id
   * @param roleIds 角色id列表
   */
  void setRoleIds(String id, List<String> roleIds);
}
