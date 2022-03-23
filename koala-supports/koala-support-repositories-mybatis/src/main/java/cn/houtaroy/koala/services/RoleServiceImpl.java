package cn.houtaroy.koala.services;

import cn.houtaroy.koala.models.Role;
import cn.houtaroy.koala.repositories.RoleRepository;
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
