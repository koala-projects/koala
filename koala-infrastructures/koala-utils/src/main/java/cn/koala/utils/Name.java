package cn.koala.utils;

/**
 * 名称
 *
 * @param singular 单数形式
 * @param plural   负数形式
 * @author Houtaroy
 */
public record Name(String singular, String plural) {
  /**
   * 根据单数形式创建名称
   *
   * @param singular 单数形式
   * @return 名称对象
   */
  public static Name fromSingular(String singular) {
    return new Name(singular, WordUtil.plural(singular));
  }
}
