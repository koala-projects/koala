package cn.koala.system.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.system.Department;
import cn.koala.toolkit.tree.TreeNode;

import java.util.List;

/**
 * 字典服务接口
 *
 * @author Houtaroy
 */
public interface DepartmentService extends CrudService<Department, Long>, PagingService<Department, Long> {
  /**
   * 查询全部部门, 以树形形式返回
   *
   * @return 部门树列表
   */
  List<TreeNode> tree();
}
