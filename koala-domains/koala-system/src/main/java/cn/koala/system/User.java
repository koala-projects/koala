package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;

/**
 * 用户数据实体接口
 *
 * @author Houtaroy
 */
public interface User extends IdModel<Long>, SortModel, StateModel, AuditModel<Long> {
  String getUsername();

  String getPassword();

  void setPassword(String password);

  String getNickname();

  String getAvatar();

  String getEmail();

  String getMobile();

  String getRemark();
}
