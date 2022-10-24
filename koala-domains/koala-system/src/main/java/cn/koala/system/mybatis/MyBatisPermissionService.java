package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.Permission;
import cn.koala.system.PermissionService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 权限服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class MyBatisPermissionService extends AbstractUUIDCrudService<Permission> implements PermissionService {
  protected final PermissionRepository repository;
}
