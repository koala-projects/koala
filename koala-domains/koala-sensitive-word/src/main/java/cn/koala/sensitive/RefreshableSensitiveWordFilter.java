package cn.koala.sensitive;

import java.util.List;

/**
 * 支持刷新词库的敏感词过滤器接口
 *
 * @author Houtaroy
 */
public interface RefreshableSensitiveWordFilter extends SensitiveWordFilter {
  void refresh(List<String> words);
}
