package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Sortable;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface Permission extends Idable<String>, Codeable, Sortable, Stateable, Auditable {

  /**
   * 获取权限类型
   *
   * @return 权限类型
   */
  PermissionType getType();

  /**
   * 获取上级权限
   *
   * @return 上级权限
   */
  Permission getParent();

  /**
   * 获取接口列表
   *
   * @return 接口列表
   */
  List<? extends Api> getApis();
}
