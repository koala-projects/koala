package cn.koala.system.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.system.User;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author Houtaroy
 */
public interface UserService extends CrudService<User, Long>, PagingService<User, Long> {
  List<Long> getRoleIds(Long id);

  void setRoleIds(Long id, List<Long> roleIds);

  List<Long> getDepartmentIds(Long id);

  void setDepartmentIds(Long id, List<Long> departmentIds);
}
