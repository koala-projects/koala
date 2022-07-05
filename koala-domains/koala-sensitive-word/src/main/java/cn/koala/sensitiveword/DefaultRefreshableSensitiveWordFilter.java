package cn.koala.sensitiveword;

import toolgood.words.StringSearchEx2;

/**
 * 默认可刷新敏感词库的敏感词过滤器
 *
 * @author Houtaroy
 */
public class DefaultRefreshableSensitiveWordFilter extends AbstractSensitiveWordFilter
  implements RefreshableSensitiveWordFilter {
  protected final SensitiveWordRepository repository;

  /**
   * 构造函数
   *
   * @param repository 敏感词存储库类
   */
  public DefaultRefreshableSensitiveWordFilter(SensitiveWordRepository repository) {
    this.repository = repository;
    this.init();
  }

  @Override
  public void refresh() {
    init();
  }

  @Override
  protected void init() {
    StringSearchEx2 result = new StringSearchEx2();
    result.SetKeywords(repository.findAll());
    instance = result;
  }
}
