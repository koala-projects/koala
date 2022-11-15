package cn.koala.persistence;

import cn.koala.enhancement.YesNo;

/**
 * @author Houtaroy
 */
public interface Deletable {
  /**
   * 获取是否删除
   *
   * @return 是否删除
   */
  YesNo getIsDelete();
}
