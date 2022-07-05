package cn.koala.sensitiveword;

/**
 * 敏感词过滤器
 *
 * @author Houtaroy
 */
public interface SensitiveWordFilter {
  char DEFAULT_REPLACEMENT = '*';

  /**
   * 过滤文本敏感词
   *
   * @param content 文本
   * @return 过滤后的文本
   */
  default String doFilter(String content) {
    return doFilter(content, DEFAULT_REPLACEMENT);
  }

  /**
   * 使用指定字符过滤文本敏感词
   *
   * @param content     文本
   * @param replacement 替换字符
   * @return 过滤后的文本
   */
  String doFilter(String content, char replacement);
}
