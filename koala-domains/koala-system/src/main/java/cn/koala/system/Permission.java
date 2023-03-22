package cn.koala.system;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 权限数据实体接口
 *
 * @author Houtaroy
 */
public interface Permission extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {
  String getCode();

  String getName();

  PermissionType getType();

  String getIcon();

  String getUrl();

  String getComponent();

  String getRemark();

  Long getParentId();
}
