package cn.koala.template.domain;

import java.util.Map;

/**
 * 模板渲染器
 *
 * @author Houtaroy
 */
public interface TemplateRenderer {

  String render(Template template, Map<?, ?> context);
}
