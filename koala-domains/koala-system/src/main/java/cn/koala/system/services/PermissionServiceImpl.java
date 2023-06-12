package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.Permission;
import cn.koala.system.repositories.PermissionRepository;
import cn.koala.toolkit.tree.TreeHelper;
import cn.koala.toolkit.tree.TreeNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 权限服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class PermissionServiceImpl extends AbstractMyBatisService<Permission, Long> implements PermissionService {

  protected final PermissionRepository repository;

  @Override
  public List<TreeNode> tree() {
    return TreeHelper.build(
      list(Map.of()),
      permission -> new TreeNode(permission.getId(), permission.getName(), permission.getParentId(), permission)
    );
  }
}
