package cn.koala.system.service;

import cn.koala.data.service.CrudService;
import cn.koala.system.domain.Role;
import cn.koala.system.domain.User;

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
