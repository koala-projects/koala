package cn.koala.system.mybatis;

import cn.koala.system.Permission;
import cn.koala.system.PermissionService;
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
public class MyBatisPermissionService extends AbstractCrudService<String, Permission> implements PermissionService {

  protected final PermissionRepository repository;
}
