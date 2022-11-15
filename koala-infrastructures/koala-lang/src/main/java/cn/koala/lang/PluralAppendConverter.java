package cn.koala.lang;

/**
 * 追加单词复数转换器
 * 符合规则时, 在单词末尾进行追加
 * 例如: application -> applications
 *
 * @author Houtaroy
 */
public class PluralAppendConverter extends PluralRegularConverter {
  protected final String appendage;

  /**
   * 构造函数
   *
   * @param expression 正则表达式
   * @param appendage  追加内容
   */
  public PluralAppendConverter(String expression, String appendage) {
    super(expression, "");
    this.appendage = appendage;
  }

  @Override
  public String convert(String singular) {
    return singular + appendage;
  }
}
