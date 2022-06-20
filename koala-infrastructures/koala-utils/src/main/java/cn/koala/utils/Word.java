package cn.koala.utils;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Houtaroy
 */
@Getter
public class Word {
  private final String value;
  private final String plural;
  private final String capitalize;

  /**
   * 构造函数
   *
   * @param value 单词
   */
  public Word(String value) {
    this.value = value.toLowerCase();
    this.plural = WordUtil.plural(value);
    this.capitalize = StringUtils.capitalize(value);
  }
}
