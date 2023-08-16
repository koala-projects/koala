package cn.koala.system;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;

/**
 * 权限数据实体接口
 *
 * @author Houtaroy
 */
public interface Permission extends Persistable<Long>, Sortable {

  String getCode();

  String getName();

  String getRemark();

  Long getParentId();
}
