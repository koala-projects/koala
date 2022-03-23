package cn.houtaroy.koala.services;

import cn.houtaroy.koala.models.Permission;
import cn.houtaroy.koala.repositories.PermissionRepository;
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
