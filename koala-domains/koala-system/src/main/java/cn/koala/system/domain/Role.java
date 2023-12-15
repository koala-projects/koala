package cn.koala.system.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 角色数据实体接口
 *
 * @author Houtaroy
 */
public interface Role extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getCode();

  String getName();

  String getDescription();
}
