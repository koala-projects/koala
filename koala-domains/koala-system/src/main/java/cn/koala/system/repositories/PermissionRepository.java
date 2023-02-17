package cn.koala.system.repositories;

import cn.koala.mybatis.CrudRepository;
import cn.koala.system.Permission;

/**
 * 权限仓库接口
 *
 * @author Houtaroy
 */
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
