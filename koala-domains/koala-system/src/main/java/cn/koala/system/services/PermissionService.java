package cn.koala.system.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.system.Permission;
import cn.koala.toolkit.tree.TreeNode;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author Houtaroy
 */
public interface PermissionService extends CrudService<Permission, Long>, PagingService<Permission, Long> {
  /**
   * 查询全部部门, 以树形形式返回
   *
   * @return 部门树列表
   */
  List<TreeNode> tree();
}
