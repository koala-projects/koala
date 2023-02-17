package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;

/**
 * 权限数据实体接口
 *
 * @author Houtaroy
 */
public interface Permission extends IdModel<Long>, SortModel, StateModel, AuditModel<Long> {
  String getCode();

  String getName();

  PermissionType getType();

  String getIcon();

  String getUrl();

  String getComponent();

  String getRemark();

  Long getParentId();
}
