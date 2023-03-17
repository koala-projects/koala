package cn.koala.sensitive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 内存敏感词服务接口
 *
 * @author Houtaroy
 */
public class InMemorySensitiveWordService implements SensitiveWordService {
  protected final Set<String> words;

  public InMemorySensitiveWordService() {
    words = new HashSet<>();
  }

  public InMemorySensitiveWordService(List<String> words) {
    this.words = new HashSet<>(words);
  }

  @Override
  public List<String> list() {
    return new ArrayList<>(words);
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
