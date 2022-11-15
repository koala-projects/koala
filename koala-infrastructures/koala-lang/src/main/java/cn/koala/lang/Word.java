package cn.koala.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单词类
 * <p>
 * 包含单数/复数
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
  private String singular;
  private String plural;

  /**
   * 根据单数形式创建单词
   *
   * @param singular 单词单数形式
   * @return 单词对象
   */
  public static Word fromSingular(String singular) {
    return new Word(singular, LangHelper.plural(singular));
  }
}
