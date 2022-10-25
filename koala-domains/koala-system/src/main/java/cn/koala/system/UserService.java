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
   * 根据id获取角色id列表
   *
   * @param id 用户id
   * @return 角色id列表
   */
  List<String> roleIds(String id);

  /**
   * 设置用户角色
   *
   * @param id      用户id
   * @param roleIds 角色id列表
   */
  void setRoles(String id, List<String> roleIds);
}
