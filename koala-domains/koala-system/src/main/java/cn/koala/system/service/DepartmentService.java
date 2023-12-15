package cn.koala.system.service;

import cn.koala.data.service.CrudService;
import cn.koala.system.domain.Department;
import cn.koala.util.TreeNode;

import java.util.List;

/**
 * 字典服务接口
 *
 * @author Houtaroy
 */
public interface DepartmentService extends CrudService<Department, Long> {
  
  List<TreeNode> tree();
}
