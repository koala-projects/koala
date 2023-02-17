package cn.koala.mybatis;

/**
 * 增强枚举
 *
 * @author Houtaroy
 */
public interface EnhancedEnum {
  /**
   * 获取枚举值
   *
   * @return 枚举值
   */
  int getValue();

  /**
   * 获取枚举名称
   *
   * @return 枚举名称
   */
  String getName();
}
