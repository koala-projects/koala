package cn.koala.codegen.name;

import org.springframework.lang.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 替换复数转换器
 *
 * @author Houtaroy
 */
public class ReplacePluralConverter extends AbstractRegularPluralConverter {
  
  private final String replacement;

  public ReplacePluralConverter(Pattern pattern, String replacement) {
    super(pattern);
    this.replacement = replacement;
  }

  @Override
  public String convert(@NonNull String singular) {
    Matcher matcher = pattern.matcher(singular);
    if (matcher.find()) {
      return matcher.replaceAll(replacement);
    }
    return singular;
  }
}
