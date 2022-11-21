package cn.koala.template;

import com.jfinal.template.Engine;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Enjoy渲染器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class EnjoyRenderer implements Renderer {

  protected final Engine engine;

  @Override
  public Map<String, String> render(Template template, Map<String, Object> data) {
    return Map.of(
      engine.getTemplateByString(template.getName(), "%s-name".formatted(template.getId())).renderToString(data),
      engine.getTemplateByString(template.getContent(), template.getId()).renderToString(data)
    );
  }
}
