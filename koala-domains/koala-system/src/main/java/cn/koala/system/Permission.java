package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;

/**
 * @author Houtaroy
 */
public interface Permission extends Idable<String>, Codeable {

    /**
     * 获取权限类型
     *
     * @return 权限类型
     */
    PermissionType getType();
}
