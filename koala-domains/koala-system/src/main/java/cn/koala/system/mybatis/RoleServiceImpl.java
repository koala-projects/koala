package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.Role;
import cn.koala.system.RoleService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 角色服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class RoleServiceImpl extends AbstractUUIDCrudService<Role> implements RoleService {
  protected final RoleRepository repository;
  protected final RolePermissionRepository rolePermissionRepository;

  @Override
  public void delete(Role entity) {
    rolePermissionRepository.deleteByRoleId(entity.getId());
    super.delete(entity);
  }

  @Override
  public List<String> permissionIds(String id) {
    return rolePermissionRepository.findAllPermissionIdByRoleId(id);
  }

  @Override
  public void authorize(String id, List<String> permissionIds) {
    rolePermissionRepository.deleteByRoleId(id);
    rolePermissionRepository.add(id, permissionIds);
  }
}
