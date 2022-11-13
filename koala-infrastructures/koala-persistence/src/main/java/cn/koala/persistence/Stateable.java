package cn.koala.persistence;

import cn.koala.enhancement.YesNo;

/**
 * @author Houtaroy
 */
public interface Stateable {

  /**
   * 获取是否系统
   *
   * @return 是否系统
   */
  YesNo getIsSystem();

  /**
   * 获取是否启用
   *
   * @return 是否启用
   */
  YesNo getIsEnable();

  /**
   * 获取是否删除
   *
   * @return 是否删除
   */
  YesNo getIsDelete();

  /**
   * 设置是否删除属性
   *
   * @param isDelete 是否枚举
   */
  void setIsDelete(YesNo isDelete);
}
