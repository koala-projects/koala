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
  public String render(String content, Map<String, Object> data) {
    return engine.getTemplateByString(content).renderToString(data);
  }
}
