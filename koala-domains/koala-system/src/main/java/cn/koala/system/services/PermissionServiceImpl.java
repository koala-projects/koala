package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.Permission;
import cn.koala.system.repositories.PermissionRepository;
import cn.koala.toolkit.tree.TreeHelper;
import cn.koala.toolkit.tree.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * 权限服务实现类
 *
 * @author Houtaroy
 */
public class PermissionServiceImpl extends AbstractMyBatisService<Permission, Long> implements PermissionService {
  public PermissionServiceImpl(PermissionRepository repository) {
    super(repository);
  }

  @Override
  public List<TreeNode> tree() {
    return TreeHelper.build(
      read(Map.of()),
      permission -> new TreeNode(permission.getId(), permission.getName(), permission.getParentId(), permission)
    );
  }
}
