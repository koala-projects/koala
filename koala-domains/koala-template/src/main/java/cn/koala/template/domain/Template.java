package cn.koala.template.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 模板接口
 *
 * @author Houtaroy
 */
public interface Template extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  Long getGroupId();

  String getName();

  String getContent();

  String getDescription();
}
