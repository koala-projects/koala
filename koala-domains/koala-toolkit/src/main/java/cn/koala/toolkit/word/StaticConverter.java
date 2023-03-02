package cn.koala.toolkit.word;

import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 静态转换器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class StaticConverter implements WordConverter {
  private final Map<String, String> words;

  @Override
  public boolean isSupported(String word) {
    return words.containsKey(word);
  }

  @Override
  public String convert(String word) {
    return words.getOrDefault(word, word);
  }
}
