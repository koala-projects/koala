package cn.koala.system;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 字典项接口
 *
 * @author Houtaroy
 */
public interface DictionaryItem extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {
  String getCode();

  String getName();

  String getRemark();

  Long getDictionaryId();
}
