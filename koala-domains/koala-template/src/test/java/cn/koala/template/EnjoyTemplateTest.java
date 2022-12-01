package cn.koala.template;

import com.jfinal.template.Engine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author Houtaroy
 */
public class EnjoyTemplateTest {
  @Test
  public void render() {
    Assertions.assertEquals(
      new EnjoyRenderer(Engine.use()).render("#(test)", Map.of("test", "koala")),
      "koala"
    );
  }

  @Test
  public void stringExt() {
    Engine.addExtensionMethod(String.class, EnjoyStringExt.class);
    Assertions.assertEquals(
      new EnjoyRenderer(Engine.use()).render("#(test.capitalize())", Map.of("test", "koala")),
      "Koala"
    );
  }
}
