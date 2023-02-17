package cn.koala.system.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.system.Role;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author Houtaroy
 */
public interface RoleService extends CrudService<Role, Long>, PagingService<Role, Long> {
  List<Long> getCheckedPermissionIds(Long id);

  void authorize(Long id, List<Long> checkedIds, List<Long> halfCheckedIds);
}
