package cn.koala.query.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 查询定义接口
 *
 * @author Houtaroy
 */
public interface Query extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getName();

  String getDescription();

  String getSql();
}
