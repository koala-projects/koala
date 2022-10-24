package cn.koala.system.mybatis;

import cn.koala.mybatis.PageRepository;
import cn.koala.system.Permission;

/**
 * 权限存储库
 *
 * @author Houtaroy
 */
public interface PermissionRepository extends PageRepository<String, Permission> {
}
