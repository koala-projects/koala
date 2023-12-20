package cn.koala.system.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.domain.Permission;
import cn.koala.system.repository.PermissionRepository;
import cn.koala.util.TreeNode;
import cn.koala.util.TreeUtils;
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
public class DefaultPermissionService extends AbstractSmartService<Permission, Long> implements PermissionService {

  protected final PermissionRepository repository;

  @Override
  public List<TreeNode> tree() {
    return TreeUtils.build(
      list(Map.of()),
      permission -> new TreeNode(permission.getId(), permission.getName(), permission.getParentId(), permission)
    );
  }
}
