package cn.koala.system.model;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public interface Duty extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {

  String getCode();

  String getName();

  String getDescription();
}
