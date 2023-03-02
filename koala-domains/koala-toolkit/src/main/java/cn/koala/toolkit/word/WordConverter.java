package cn.koala.toolkit.word;

/**
 * 单词转换器
 *
 * @author Houtaroy
 */
public interface WordConverter {
  /**
   * 是否符合转换要求
   *
   * @param word 单词
   * @return 结果
   */
  boolean isSupported(String word);

  /**
   * 转换
   *
   * @param word 单词
   * @return 转换结果
   */
  String convert(String word);
}