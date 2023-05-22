package cn.koala.sensitive.support;

import cn.koala.sensitive.RefreshableSensitiveWordFilter;
import cn.koala.sensitive.SensitiveWordFilter;

import java.util.List;

/**
 * 聚合敏感词过滤器
 *
 * @author Houtaroy
 */
public class CompositeSensitiveWordFilter implements RefreshableSensitiveWordFilter {

  private final List<SensitiveWordFilter> filters;
  private final List<RefreshableSensitiveWordFilter> refreshableFilters;

  public CompositeSensitiveWordFilter(List<SensitiveWordFilter> filters) {
    this.filters = filters;
    this.refreshableFilters = filters.stream()
      .filter(filter -> filter instanceof RefreshableSensitiveWordFilter)
      .map(RefreshableSensitiveWordFilter.class::cast)
      .toList();
  }

  @Override
  public String filter(String content, Character replacement) {
    String result = content;
    for (SensitiveWordFilter filter : filters) {
      result = filter.filter(content, replacement);
    }
    return result;
  }

  @Override
  public void refresh() {
    this.refreshableFilters.forEach(RefreshableSensitiveWordFilter::refresh);
  }
}
