package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.Role;
import cn.koala.system.RoleEntity;
import cn.koala.system.RoleService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

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
  protected final UserRepository userRepository;
  protected final RolePermissionRepository rolePermissionRepository;

  @Override
  public void delete(Role entity) {
    Assert.isTrue(isNoUser(entity), "角色下有用户, 无法删除");
    rolePermissionRepository.deleteByRoleId(entity.getId());
    super.delete(entity);
  }

  /**
   * 角色下是否无用户
   *
   * @param entity 角色数据实体
   * @return 是否无用户
   */
  public boolean isNoUser(Role entity) {
    return userRepository.findAll(Map.of("roleId", entity.getId())).isEmpty();
  }

  @Override
  public List<String> getPermissionIds(String id) {
    return rolePermissionRepository.findAllPermissionIdByRoleId(id);
  }

  @Override
  public void setPermissionIds(String id, List<String> permissionIds) {
    Assert.isTrue(isNoSystem(RoleEntity.builder().id(id).build()), "权限不足, 请联系管理员");
    rolePermissionRepository.deleteByRoleId(id);
    if (permissionIds.isEmpty()) {
      return;
    }
    rolePermissionRepository.add(id, permissionIds);
  }
}
