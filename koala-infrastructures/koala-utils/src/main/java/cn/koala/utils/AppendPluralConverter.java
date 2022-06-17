package cn.koala.utils;

/**
 * @author Houtaroy
 */
public class AppendPluralConverter extends RegularPluralConverter {
  protected final String appendage;

  /**
   * 构造函数
   *
   * @param expression 正则表达式
   * @param appendage  追加内容
   */
  public AppendPluralConverter(String expression, String appendage) {
    super(expression, "");
    this.appendage = appendage;
  }

  @Override
  public String convert(String singular) {
    return singular + appendage;
  }
}
