package cn.koala.sensitiveword;

import lombok.extern.slf4j.Slf4j;
import toolgood.words.StringSearchEx2;

/**
 * 文件敏感词过滤器
 *
 * @author Houtaroy
 */
@Slf4j
public class FileSensitiveWordFilter extends AbstractSensitiveWordFilter {
  private final String location;

  /**
   * 文件敏感词过滤器构造函数
   *
   * @param location 文件全路径名
   */
  public FileSensitiveWordFilter(String location) {
    this.location = location;
    init();
  }

  @Override
  public void init() {
    StringSearchEx2 result = new StringSearchEx2();
    try {
      result.Load(location);
      instance = result;
    } catch (Exception e) {
      LOGGER.error("读取敏感词库资源[{}]失败", location, e);
    }
  }
}
