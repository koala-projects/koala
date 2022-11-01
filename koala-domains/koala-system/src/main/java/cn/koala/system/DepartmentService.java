package cn.koala.system;

import cn.koala.persistence.CrudService;
import cn.koala.utils.TreeNode;
import cn.koala.utils.TreeUtil;

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
    return TreeUtil.build(
      list(Map.of()),
      department -> new TreeNode<>(department.getId(), department.getName(), department.getParentId(), department)
    );
  }
}
