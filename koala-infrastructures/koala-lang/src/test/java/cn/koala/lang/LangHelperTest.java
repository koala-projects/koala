package cn.koala.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Houtaroy
 */
public class LangHelperTest {
  /**
   * 单词转换为复数测试
   */
  @Test
  public void plural() {
    Assertions.assertEquals(LangHelper.plural("person"), "people");
    Assertions.assertEquals(LangHelper.plural("sheep"), "sheep");
    Assertions.assertEquals(LangHelper.plural("laf"), "laves");
    Assertions.assertEquals(LangHelper.plural("cafe"), "caves");
    Assertions.assertEquals(LangHelper.plural("boy"), "boys");
    Assertions.assertEquals(LangHelper.plural("fly"), "flies");
    Assertions.assertEquals(LangHelper.plural("lash"), "lashes");
    Assertions.assertEquals(LangHelper.plural("test"), "tests");
  }
}
