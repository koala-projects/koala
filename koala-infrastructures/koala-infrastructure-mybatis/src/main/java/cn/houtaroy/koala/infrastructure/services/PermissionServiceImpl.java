package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.infrastructure.repositories.PermissionRepository;
import cn.koala.system.models.Permission;
import cn.koala.system.services.PermissionService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class PermissionServiceImpl extends BaseCrudService<String, Permission> implements PermissionService {

  private final PermissionRepository repository;
}
