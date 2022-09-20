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

  /**
   * 根据字符串模板渲染
   *
   * @param content 字符串模板
   * @param data    数据
   * @return 渲染结果
   */
  public String render(String content, Map<?, ?> data) {
    return engine.getTemplateByString(content).renderToString(data);
  }
}
