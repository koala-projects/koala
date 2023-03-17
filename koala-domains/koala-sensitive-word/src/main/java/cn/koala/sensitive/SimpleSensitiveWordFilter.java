package cn.koala.sensitive;

import lombok.RequiredArgsConstructor;
import toolgood.words.WordsSearch;

import java.util.List;

/**
 * 基于ToolGoodWords的简易敏感词过滤器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SimpleSensitiveWordFilter implements RefreshableSensitiveWordFilter {
  protected final WordsSearch instance;

  public SimpleSensitiveWordFilter(List<String> words) {
    this(new WordsSearch());
    refresh(words);
  }

  @Override
  public String filter(String content, Character replacement) {
    return instance.Replace(content, replacement);
  }

  @Override
  public void refresh(List<String> words) {
    instance.SetKeywords(words);
  }
}
