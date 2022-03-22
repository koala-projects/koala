package cn.houtaroy.koala.models;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface Role extends Idable<String>, Codeable, Sortable, Stateable, Auditable {

  /**
   * 获取权限列表
   *
   * @return 权限列表
   */
  List<? extends Permission> getPermissions();
}
