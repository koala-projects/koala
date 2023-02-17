package cn.koala.system.services;

import cn.koala.system.Department;
import cn.koala.system.repositories.DepartmentRepository;
import cn.koala.toolkit.tree.TreeHelper;
import cn.koala.toolkit.tree.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * 部门服务实现类
 *
 * @author Houtaroy
 */
public class DepartmentServiceImpl extends BaseSystemService<Department> implements DepartmentService {
  public DepartmentServiceImpl(DepartmentRepository repository) {
    super(repository);
  }

  @Override
  public List<TreeNode> tree() {
    return TreeHelper.build(
      list(Map.of()),
      department -> new TreeNode(department.getId(), department.getName(), department.getParentId(), department)
    );
  }
}
