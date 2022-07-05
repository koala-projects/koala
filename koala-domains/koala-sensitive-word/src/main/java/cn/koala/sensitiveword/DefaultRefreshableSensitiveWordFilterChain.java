package cn.koala.sensitiveword;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 默认可刷新敏感词的敏感词过滤链
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultRefreshableSensitiveWordFilterChain implements RefreshableSensitiveWordFilterChain {
  protected final List<SensitiveWordFilter> filters;

  @Override
  public String doFilter(String content, char replacement) {
    String result = content;
    for (SensitiveWordFilter filter : filters) {
      result = filter.doFilter(result, replacement);
    }
    return result;
  }

  @Override
  public void refresh() {
    synchronized (this) {
      filters.forEach(this::refreshFilter);
    }
  }

  @Override
  public List<SensitiveWordFilter> getFilters() {
    return filters;
  }

  /**
   * 刷新敏感词过滤器
   *
   * @param filter 敏感词过滤器
   */
  protected void refreshFilter(SensitiveWordFilter filter) {
    if (filter instanceof RefreshableSensitiveWordFilter) {
      ((RefreshableSensitiveWordFilter) filter).refresh();
    }
  }
}
