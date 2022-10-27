package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;

/**
 * 部门
 *
 * @author Houtaroy
 */
public interface Department extends Idable<String>, Codeable {
  /**
   * 获取上级部门
   *
   * @return 上级部门
   */
  Department getParent();
}
