package cn.koala.system.services;

import cn.koala.system.models.Role;
import cn.koala.system.repositories.RoleRepository;
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
