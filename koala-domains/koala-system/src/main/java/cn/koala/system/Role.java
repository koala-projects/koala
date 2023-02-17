package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;

/**
 * 角色数据实体接口
 *
 * @author Houtaroy
 */
public interface Role extends IdModel<Long>, SortModel, StateModel, AuditModel<Long> {
  String getCode();

  String getName();

  String getRemark();
}
