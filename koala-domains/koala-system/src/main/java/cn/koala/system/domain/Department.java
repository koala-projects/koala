package cn.koala.system.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 部门接口
 *
 * @author Houtaroy
 */
public interface Department extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  Long getParentId();

  String getName();

  String getDescription();
}
