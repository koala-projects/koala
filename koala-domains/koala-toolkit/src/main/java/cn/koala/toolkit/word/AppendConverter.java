package cn.koala.toolkit.word;

/**
 * 追加转换器
 *
 * @author Houtaroy
 */
public class AppendConverter extends BaseRegularConverter {
  private final String appendage;

  public AppendConverter(String pattern, String appendage) {
    super(pattern);
    this.appendage = appendage;
  }

  @Override
  public String convert(String singular) {
    return singular + appendage;
  }
}
