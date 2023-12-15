package cn.koala.system.model;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;

/**
 * 权限接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface Permission extends Persistable<Long>, Sortable {

  String getCode();

  String getName();

  void setName(String name);

  String getRemark();

  Long getParentId();

  void setParentId(Long parentId);
}
