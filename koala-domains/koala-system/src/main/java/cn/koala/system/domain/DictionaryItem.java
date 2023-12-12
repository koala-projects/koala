package cn.koala.system.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 字典项接口
 *
 * @author Houtaroy
 */
public interface DictionaryItem extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getCode();

  String getName();

  String getDescription();

  Long getDictionaryId();
}
