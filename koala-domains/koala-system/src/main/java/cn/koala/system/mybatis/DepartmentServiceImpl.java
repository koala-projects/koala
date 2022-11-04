package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.Department;
import cn.koala.system.DepartmentService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.Map;

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
  protected final UserRepository userRepository;

  @Override
  public void delete(Department entity) {
    Assert.isTrue(isNoUser(entity), "部门下有用户, 无法删除");
    super.delete(entity);
  }

  /**
   * 部门下是否无用户
   *
   * @param entity 部门数据实体
   * @return 是否无用户
   */
  protected boolean isNoUser(Department entity) {
    return userRepository.findAll(Map.of("departmentId", entity.getId())).isEmpty();
  }
}
