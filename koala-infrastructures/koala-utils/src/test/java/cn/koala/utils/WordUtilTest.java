package cn.koala.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Houtaroy
 */
public class WordUtilTest {
  /**
   * 单词转换为复数测试
   */
  @Test
  public void plural() {
    Assertions.assertEquals(WordUtil.plural("person"), "people");
    Assertions.assertEquals(WordUtil.plural("sheep"), "sheep");
    Assertions.assertEquals(WordUtil.plural("laf"), "laves");
    Assertions.assertEquals(WordUtil.plural("cafe"), "caves");
    Assertions.assertEquals(WordUtil.plural("boy"), "boys");
    Assertions.assertEquals(WordUtil.plural("fly"), "flies");
    Assertions.assertEquals(WordUtil.plural("lash"), "lashes");
    Assertions.assertEquals(WordUtil.plural("test"), "tests");
  }
}
