package cn.koala.system.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.system.domain.Department;

/**
 * 部门仓库接口
 *
 * @author Houtaroy
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
