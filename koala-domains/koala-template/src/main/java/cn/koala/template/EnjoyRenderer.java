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
  public String render(Template template, Map<?, ?> data) {
    return engine.getTemplate(new EnjoyTemplate(template)).renderToString(data);
  }
}
