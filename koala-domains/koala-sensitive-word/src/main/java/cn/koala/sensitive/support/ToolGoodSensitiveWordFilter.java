package cn.koala.sensitive.support;

import cn.koala.sensitive.RefreshableSensitiveWordFilter;
import cn.koala.sensitive.SensitiveWordService;
import toolgood.words.WordsSearch;

/**
 * 基于<a href="https://github.com/toolgood/ToolGood.Words">ToolGood.Words</a>的敏感词过滤器
 *
 * @author Houtaroy
 */
public class ToolGoodSensitiveWordFilter implements RefreshableSensitiveWordFilter {

  private final SensitiveWordService service;
  private final WordsSearch instance;

  public ToolGoodSensitiveWordFilter(SensitiveWordService service) {
    this.service = service;
    this.instance = new WordsSearch();
  }

  @Override
  public String filter(String content, Character replacement) {
    return this.instance.Replace(content, replacement);
  }

  @Override
  public void refresh() {
    this.instance.SetKeywords(this.service.list());
  }
}
