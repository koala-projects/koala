package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;

/**
 * 部门数据实体接口
 *
 * @author Houtaroy
 */
public interface Department extends IdModel<Long>, SortModel, StateModel, AuditModel<Long> {
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
