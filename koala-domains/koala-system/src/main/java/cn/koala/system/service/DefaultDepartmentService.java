package cn.koala.system.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.domain.Department;
import cn.koala.system.repository.DepartmentRepository;
import cn.koala.util.TreeNode;
import cn.koala.util.TreeUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 部门服务实现类
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class DefaultDepartmentService extends AbstractSmartService<Department, Long> implements DepartmentService {

  private final DepartmentRepository repository;

  @Override
  public List<TreeNode> tree() {
    return TreeUtils.build(
      list(Map.of()),
      department -> new TreeNode(department.getId(), department.getName(), department.getParentId(), department)
    );
  }
}
