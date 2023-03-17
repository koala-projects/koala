package cn.koala.sensitive;

import java.util.List;

/**
 * 敏感词服务接口
 *
 * @author Houtaroy
 */
public interface SensitiveWordService {
  List<String> list();

  void add(String word);

  void delete(String word);
}
