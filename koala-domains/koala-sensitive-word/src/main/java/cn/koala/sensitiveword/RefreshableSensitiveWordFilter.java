package cn.koala.sensitiveword;

/**
 * 可刷新词库的敏感词过滤器
 *
 * @author Houtaroy
 */
public interface RefreshableSensitiveWordFilter extends SensitiveWordFilter {
  /**
   * 刷新敏感词库
   */
  void refresh();
}
