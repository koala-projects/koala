package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface Role extends Idable<String>, Codeable {

  /**
   * 获取权限列表
   *
   * @return 权限列表
   */
  List<? extends Permission> getPermissions();
}
