package cn.koala.sensitive.support;

import cn.koala.sensitive.SensitiveWordService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 内存敏感词服务接口
 *
 * @author Houtaroy
 */
public class InMemorySensitiveWordService implements SensitiveWordService {
  protected final List<String> words;

  public InMemorySensitiveWordService() {
    this(new ArrayList<>());
  }

  public InMemorySensitiveWordService(List<String> words) {
    this.words = words;
  }

  @Override
  public List<String> list() {
    return Collections.unmodifiableList(words);
  }

  @Override
  public void add(String word) {
    words.add(word);
  }

  @Override
  public void delete(String word) {
    words.remove(word);
  }
}
