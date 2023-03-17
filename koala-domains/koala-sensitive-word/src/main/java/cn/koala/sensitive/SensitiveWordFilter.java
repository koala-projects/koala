package cn.koala.sensitive;

/**
 * 敏感词过滤器
 *
 * @author Houtaroy
 */
public interface SensitiveWordFilter {
  String filter(String content, Character replacement);
}
