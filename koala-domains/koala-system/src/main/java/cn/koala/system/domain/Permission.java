package cn.koala.system.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 权限接口
 *
 * @author Houtaroy
 */
public interface Permission extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  Long getParentId();

  void setParentId(Long parentId);

  String getCode();

  String getName();

  void setName(String name);

  String getDescription();
}
