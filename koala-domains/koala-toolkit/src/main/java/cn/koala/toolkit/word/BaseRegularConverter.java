package cn.koala.toolkit.word;

import java.util.regex.Pattern;

/**
 * 正则转换器抽象类
 * <p>
 * 会依据正则表达式对单词进行匹配, 符合的再继续进行转换
 *
 * @author Houtaroy
 */
public abstract class BaseRegularConverter implements WordConverter {
  protected final Pattern pattern;

  public BaseRegularConverter(String pattern) {
    this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
  }

  @Override
  public boolean isSupported(String word) {
    return pattern.matcher(word).find();
  }
}
