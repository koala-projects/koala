package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.Role;
import cn.koala.system.RoleService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 角色服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class MyBatisRoleService extends AbstractUUIDCrudService<Role> implements RoleService {
  protected final RoleRepository repository;
}
