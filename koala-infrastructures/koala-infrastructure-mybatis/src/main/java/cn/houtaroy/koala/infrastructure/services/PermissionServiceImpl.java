package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.domain.models.Permission;
import cn.houtaroy.koala.domain.services.PermissionService;
import cn.houtaroy.koala.infrastructure.repositories.PermissionRepository;
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
