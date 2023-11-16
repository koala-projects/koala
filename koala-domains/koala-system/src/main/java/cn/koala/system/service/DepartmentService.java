package cn.koala.system.service;

import cn.koala.persist.CrudService;
import cn.koala.system.model.Department;
import cn.koala.util.TreeNode;

import java.util.List;

/**
 * 字典服务接口
 *
 * @author Houtaroy
 */
public interface DepartmentService extends CrudService<Department, Long> {
  /**
   * 查询全部部门, 以树形形式返回
   *
   * @return 部门树列表
   */
  List<TreeNode> tree();
}
