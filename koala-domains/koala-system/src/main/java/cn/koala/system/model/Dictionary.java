package cn.koala.system.model;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 字典数据实体接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface Dictionary extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {
  /**
   * 获取字典代码
   *
   * @return 字典代码
   */
  String getCode();

  /**
   * 获取字典名称
   *
   * @return 字典名称
   */
  String getName();

  /**
   * 获取字典备注
   *
   * @return 字典备注
   */
  String getRemark();
}
