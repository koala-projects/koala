package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.domain.models.Role;
import cn.houtaroy.koala.domain.services.RoleService;
import cn.houtaroy.koala.infrastructure.repositories.RoleRepository;
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
