package cn.koala.system.service;

import cn.koala.persist.CrudService;
import cn.koala.system.model.Role;
import cn.koala.system.model.User;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author Houtaroy
 */
public interface RoleService extends CrudService<Role, Long> {
  List<Long> getCheckedPermissionIds(Long id);

  void authorize(Long id, List<Long> checkedIds, List<Long> halfCheckedIds);

  List<User> listUser(Long id);
}
