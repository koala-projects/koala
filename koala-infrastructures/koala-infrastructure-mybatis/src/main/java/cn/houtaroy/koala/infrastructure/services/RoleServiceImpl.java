package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.infrastructure.repositories.RoleRepository;
import cn.koala.system.models.Role;
import cn.koala.system.services.RoleService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseCrudService<String, Role> implements RoleService {

  private final RoleRepository repository;
}
