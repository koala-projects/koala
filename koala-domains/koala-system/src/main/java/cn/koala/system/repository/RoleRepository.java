package cn.koala.system.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.system.model.Role;
import cn.koala.system.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色仓库接口
 *
 * @author Houtaroy
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
  List<Long> findAllCheckedPermissionIdById(Long id);

  void authorize(
    @Param("id") Long id,
    @Param("checkedIds") List<Long> checkedIds,
    @Param("halfCheckedIds") List<Long> halfCheckedIds
  );

  List<User> listUserById(Long id);
}
