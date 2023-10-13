package cn.koala.system.services;

import cn.koala.persist.CrudService;
import cn.koala.system.User;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author Houtaroy
 */
public interface UserService extends CrudService<User, Long> {
  List<Long> getRoleIds(Long id);

  void setRoleIds(Long id, List<Long> roleIds);

  List<Long> getDepartmentIds(Long id);

  void setDepartmentIds(Long id, List<Long> departmentIds);
}
