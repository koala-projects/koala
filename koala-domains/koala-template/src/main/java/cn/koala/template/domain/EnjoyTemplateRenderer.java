package cn.koala.template.domain;

import com.jfinal.template.Engine;

import java.util.Map;
import java.util.Optional;

/**
 * 基于模板引擎<a href="https://gitee.com/jfinal/enjoy/tree/master">Enjoy</a>的模板渲染器
 *
 * @author Houtaroy
 */
public class EnjoyTemplateRenderer implements TemplateRenderer {

  public static final String DEFAULT_ENGINE_NAME = "koala-template";

  private final Engine engine;

  public EnjoyTemplateRenderer() {
    this(DEFAULT_ENGINE_NAME);
  }

  public EnjoyTemplateRenderer(String engineName) {
    this.engine = Optional.ofNullable(Engine.use(engineName)).orElse(Engine.create(engineName));
  }

  @Override
  public String render(Template template, Map<?, ?> context) {
    return engine.getTemplateByString(template.getContent()).renderToString(context);
  }
}
