package cn.koala.toolkit.word;

import cn.koala.toolkit.converter.Converter;

import java.util.regex.Pattern;

/**
 * 正则转换器抽象类
 * <p>
 * 会依据正则表达式对单词进行匹配, 符合的再继续进行转换
 *
 * @author Houtaroy
 */
public abstract class AbstractRegularConverter implements Converter<String, String> {
  protected final Pattern pattern;

  public AbstractRegularConverter(String pattern) {
    this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
  }

  @Override
  public boolean support(String word) {
    return pattern.matcher(word).find();
  }
}
