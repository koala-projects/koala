package cn.koala.system.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 字典实体接口
 *
 * @author Houtaroy
 */
public interface Dictionary extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

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
   * 获取字典描述
   *
   * @return 字典描述
   */
  String getDescription();
}
