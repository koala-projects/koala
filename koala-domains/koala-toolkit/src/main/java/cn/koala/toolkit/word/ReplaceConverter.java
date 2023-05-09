package cn.koala.toolkit.word;

import java.util.regex.Matcher;

/**
 * 替换转换器
 *
 * @author Houtaroy
 */
public class ReplaceConverter extends AbstractRegularConverter {
  private final String replacement;

  public ReplaceConverter(String pattern, String replacement) {
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
