package cn.koala.system;

import cn.koala.lang.tree.TreeHelper;
import cn.koala.lang.tree.TreeNode;
import cn.koala.persistence.CrudService;

import java.util.List;
import java.util.Map;

/**
 * 部门服务
 *
 * @author Houtaroy
 */
public interface DepartmentService extends CrudService<String, Department> {
  /**
   * 获取树形结构部门列表
   *
   * @return 树形结构部门列表
   */
  default List<TreeNode<Department>> tree() {
    return TreeHelper.build(
      list(Map.of()),
      department -> new TreeNode<>(department.getId(), department.getName(), department.getParentId(), department)
    );
  }
}
