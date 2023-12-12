package cn.koala.system.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.system.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户存储库类
 *
 * @author Houtaroy
 */
public interface UserRepository extends CrudRepository<User, Long> {

  List<Long> findAllRoleIdById(Long id);

  void updateRoleIdById(@Param("id") Long id, @Param("roleIds") List<Long> roleIds);

  List<Long> findAllDepartmentIdById(Long id);

  void updateDepartmentIdById(@Param("id") Long id, @Param("departmentIds") List<Long> departmentIds);

  List<Long> findAllDutyIdById(Long id);

  void updateDutyIdById(@Param("id") Long id, @Param("dutyIds") List<Long> dutyIds);
}
