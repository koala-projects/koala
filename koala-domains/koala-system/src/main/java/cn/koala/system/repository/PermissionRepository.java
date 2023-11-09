package cn.koala.system.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.system.model.Permission;

/**
 * 权限仓库接口
 *
 * @author Houtaroy
 */
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
