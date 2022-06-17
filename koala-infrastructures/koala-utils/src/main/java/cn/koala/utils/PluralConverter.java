package cn.koala.utils;

/**
 * 复数转换器
 *
 * @author Houtaroy
 */
public interface PluralConverter {
  /**
   * 是否符合转换规则
   *
   * @param singular 单数
   * @return 结果
   */
  boolean isSupported(String singular);

  /**
   * 转换
   *
   * @param singular 单数
   * @return 复数
   */
  String convert(String singular);
}
