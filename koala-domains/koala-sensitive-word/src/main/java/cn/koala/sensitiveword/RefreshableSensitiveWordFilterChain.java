package cn.koala.sensitiveword;

import java.util.List;

/**
 * 可刷新敏感词库的敏感词过滤链
 *
 * @author Houtaroy
 */
public interface RefreshableSensitiveWordFilterChain extends RefreshableSensitiveWordFilter {
  /**
   * 获取敏感词过滤器列表
   *
   * @return 敏感词过滤器列表
   */
  List<SensitiveWordFilter> getFilters();
}
