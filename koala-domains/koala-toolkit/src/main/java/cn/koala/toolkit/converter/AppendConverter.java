package cn.koala.toolkit.converter;

import java.util.regex.Pattern;

/**
 * 追加转换器
 *
 * @author Houtaroy
 */
public class AppendConverter extends AbstractRegularConverter {
  private final String appendage;

  public AppendConverter(Pattern pattern, String appendage) {
    super(pattern);
    this.appendage = appendage;
  }

  @Override
  public String convert(String singular) {
    return singular + appendage;
  }
}
