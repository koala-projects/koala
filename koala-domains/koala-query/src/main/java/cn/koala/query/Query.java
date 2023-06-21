package cn.koala.query;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 查询定义接口
 *
 * @author Houtaroy
 */
public interface Query extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {

  String getName();

  String getRemark();

  String getSql();
}
