package cn.koala.system.services;

import cn.koala.system.models.Permission;
import cn.koala.system.repositories.PermissionRepository;
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
