package cn.koala.system;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 设置数据实体接口
 *
 * @author Houtaroy
 */
public interface Setting extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {
  String getCode();

  String getName();

  String getContent();

  String getRemark();
}
