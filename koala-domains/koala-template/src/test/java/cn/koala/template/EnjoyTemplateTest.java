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
  public void render() throws Exception {
    Template template = TemplateEntity.builder().name("#(test)").content("#(test)").build();
    Map<String, Object> data = Map.of("test", "koala");
    Renderer renderer = new EnjoyRenderer(Engine.use());
    Map<String, String> result = renderer.render(template, data);
    Assertions.assertTrue(result.containsKey("koala"));
    Assertions.assertEquals(result.get("koala"), "koala");
  }

  @Test
  public void stringExt() throws Exception {
    Template template = TemplateEntity.builder().name("#(test)").content("#(test.capitalize())").build();
    Map<String, Object> data = Map.of("test", "koala");
    Engine.addExtensionMethod(String.class, EnjoyStringExt.class);
    Renderer renderer = new EnjoyRenderer(Engine.use());
    Map<String, String> result = renderer.render(template, data);
    Assertions.assertEquals(result.get("koala"), "Koala");
  }
}
