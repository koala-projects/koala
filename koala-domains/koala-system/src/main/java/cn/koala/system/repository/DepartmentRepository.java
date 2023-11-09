package cn.koala.system.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.system.model.Department;

/**
 * 部门仓库接口
 *
 * @author Houtaroy
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
