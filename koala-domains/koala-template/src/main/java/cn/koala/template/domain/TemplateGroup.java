package cn.koala.template.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 模板组接口
 *
 * @author Houtaroy
 */
public interface TemplateGroup extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getName();

  String getDescription();
}
