package cn.koala.utils;

/**
 * 文章工具类
 *
 * @author Houtaroy
 */
public final class ArticleUtil {
  private ArticleUtil() {
  }

  /**
   * 去除文章内容换行符
   * abc\n\r123 -> abc123
   *
   * @param content 文章内容
   * @return 去除换行符后的文章内容
   */
  public static String chomp(String content) {
    return content.replace("\n", "")
      .replace("\r", "")
      .replace("\t", "");
  }
}
