package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;

/**
 * 字典数据实体接口
 *
 * @author Houtaroy
 */
public interface Dictionary extends IdModel<Long>, SortModel, StateModel, AuditModel<Long> {
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
