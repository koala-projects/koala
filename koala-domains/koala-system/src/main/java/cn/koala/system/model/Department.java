package cn.koala.system.model;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 部门数据实体接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface Department extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {
  /**
   * 获取部门名称
   *
   * @return 部门名称
   */
  String getName();

  /**
   * 获取部门备注
   *
   * @return 部门备注
   */
  String getRemark();

  /**
   * 获取上级部门ID
   *
   * @return 上级部门ID
   */
  Long getParentId();
}
