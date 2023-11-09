package cn.koala.system.model;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 用户数据实体接口
 *
 * @author Houtaroy
 */
public interface User extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {
  String getUsername();

  String getPassword();

  void setPassword(String password);

  String getNickname();

  String getAvatar();

  String getEmail();

  String getMobile();

  String getRemark();
}
