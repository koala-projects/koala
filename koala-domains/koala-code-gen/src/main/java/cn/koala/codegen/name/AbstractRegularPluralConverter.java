package cn.koala.codegen.name;

import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

/**
 * 正则转换器抽象类
 * <p>
 * 会依据正则表达式对单词进行匹配, 符合的再继续进行转换
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class AbstractRegularPluralConverter implements PluralConverter {

  protected final Pattern pattern;

  @Override
  public boolean matches(String source) {
    return pattern.matcher(source).find();
  }
}
