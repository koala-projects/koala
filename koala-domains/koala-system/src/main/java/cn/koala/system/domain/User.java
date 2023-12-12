package cn.koala.system.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 用户数据实体接口
 *
 * @author Houtaroy
 */
public interface User extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getUsername();

  String getPassword();

  void setPassword(String password);

  String getNickname();

  String getAvatar();

  String getEmail();

  String getMobile();

  String getDescription();
}
