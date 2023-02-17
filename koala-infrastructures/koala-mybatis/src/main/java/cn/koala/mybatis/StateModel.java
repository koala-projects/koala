package cn.koala.mybatis;

/**
 * 状态模型
 *
 * @author Houtaroy
 */
public interface StateModel {
  /**
   * 获取是否启用
   *
   * @return 是否启用
   */
  YesNo getIsEnable();

  /**
   * 设置是否启动
   *
   * @param isEnable 是否启用
   */
  void setIsEnable(YesNo isEnable);

  /**
   * 获取是否系统
   *
   * @return 是否系统
   */
  YesNo getIsSystem();

  /**
   * 设置是否启动
   *
   * @param isSystem 是否系统
   */
  void setIsSystem(YesNo isSystem);

  /**
   * 获取是否删除
   *
   * @return 是否删除
   */
  YesNo getIsDelete();

  /**
   * 设置是否删除
   *
   * @param isDelete 是否删除
   */
  void setIsDelete(YesNo isDelete);
}
