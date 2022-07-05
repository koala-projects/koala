package cn.koala.sensitiveword;

import toolgood.words.StringSearchEx2;

/**
 * 抽象敏感词过滤器实现
 *
 * @author Houtaroy
 */
public abstract class AbstractSensitiveWordFilter implements SensitiveWordFilter {

  protected StringSearchEx2 instance;

  @Override
  public String doFilter(String content, char replacement) {
    return instance == null ? content : instance.Replace(content, replacement);
  }

  /**
   * 初始化
   */
  protected abstract void init();
}
