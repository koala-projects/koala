package cn.koala.system.service;

import cn.koala.data.service.CrudService;
import cn.koala.system.domain.Permission;
import cn.koala.util.TreeNode;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author Houtaroy
 */
public interface PermissionService extends CrudService<Permission, Long> {
  
  List<TreeNode> tree();
}
