package cn.koala.system.repository;


import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.system.domain.Permission;

/**
 * 权限仓库接口
 *
 * @author Houtaroy
 */
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
