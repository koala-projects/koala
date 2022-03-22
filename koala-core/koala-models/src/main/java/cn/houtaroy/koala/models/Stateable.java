package cn.houtaroy.koala.models;

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
}
