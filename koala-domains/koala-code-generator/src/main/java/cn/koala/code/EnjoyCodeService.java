package cn.koala.code;

import cn.koala.code.processors.ContextProcessor;
import cn.koala.code.template.Template;
import cn.koala.database.Table;
import com.jfinal.template.Engine;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enjoy模板引擎代码服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class EnjoyCodeService implements CodeService {
  protected final Engine engine;
  protected final ContextProcessor processor;

  @Override
  public Map<String, String> generate(List<Template> templates, Table table) {
    Map<String, Object> context = processor.process(table);
    return templates.stream().collect(Collectors.toMap(Template::getName, template -> generate(template, context)));
  }

  protected String generate(Template template, Map<String, Object> context) {
    return engine.getTemplate(template.getName()).renderToString(context);
  }
}
