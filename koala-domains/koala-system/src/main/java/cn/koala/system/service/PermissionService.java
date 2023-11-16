package cn.koala.system.service;

import cn.koala.persist.CrudService;
import cn.koala.system.model.Permission;
import cn.koala.util.TreeNode;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author Houtaroy
 */
public interface PermissionService extends CrudService<Permission, Long> {
  /**
   * 查询全部部门, 以树形形式返回
   *
   * @return 部门树列表
   */
  List<TreeNode> tree();
}
