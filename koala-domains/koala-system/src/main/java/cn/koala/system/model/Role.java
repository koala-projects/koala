package cn.koala.system.model;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 角色数据实体接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface Role extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {
  String getCode();

  String getName();

  String getRemark();
}
