package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.Department;
import cn.koala.system.DepartmentService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 部门服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class DepartmentServiceImpl extends AbstractUUIDCrudService<Department> implements DepartmentService {
  protected final DepartmentRepository repository;
}
