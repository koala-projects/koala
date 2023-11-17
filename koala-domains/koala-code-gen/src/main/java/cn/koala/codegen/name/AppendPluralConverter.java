package cn.koala.codegen.name;

import org.springframework.lang.NonNull;

import java.util.regex.Pattern;

/**
 * 追加复数转换器
 *
 * @author Houtaroy
 */
public class AppendPluralConverter extends AbstractRegularPluralConverter {
  
  private final String appendage;

  public AppendPluralConverter(Pattern pattern, String appendage) {
    super(pattern);
    this.appendage = appendage;
  }

  @Override
  public String convert(@NonNull String singular) {
    return singular + appendage;
  }
}
