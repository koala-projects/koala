package cn.koala.system.repositories;

import cn.koala.mybatis.CrudRepository;
import cn.koala.system.Role;
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
}
