package cn.koala.system.mybatis;

import cn.koala.system.Role;
import cn.koala.system.RoleService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@SuppressWarnings("PMD")
public class MyBatisRoleService extends AbstractSystemCrudService<String, Role> implements RoleService {

  protected final RoleRepository repository;
}
