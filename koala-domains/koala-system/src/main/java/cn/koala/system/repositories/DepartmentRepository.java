package cn.koala.system.repositories;

import cn.koala.mybatis.CrudRepository;
import cn.koala.system.Department;

/**
 * 部门仓库接口
 *
 * @author Houtaroy
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
