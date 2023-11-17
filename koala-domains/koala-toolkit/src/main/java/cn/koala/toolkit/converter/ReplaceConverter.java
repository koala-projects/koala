package cn.koala.toolkit.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 替换转换器
 *
 * @author Houtaroy
 */
@Deprecated
public class ReplaceConverter extends AbstractRegularConverter {
  private final String replacement;

  public ReplaceConverter(Pattern pattern, String replacement) {
    super(pattern);
    this.replacement = replacement;
  }

  @Override
  public String convert(String word) {
    Matcher matcher = pattern.matcher(word);
    if (matcher.find()) {
      return matcher.replaceAll(replacement);
    }
    return word;
  }
}
