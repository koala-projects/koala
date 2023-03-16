package cn.koala.code.services;

import cn.koala.code.Code;
import cn.koala.code.processors.ContextProcessor;
import cn.koala.database.DatabaseTable;
import cn.koala.template.Template;
import cn.koala.template.TemplateGroup;
import cn.koala.template.services.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 基于模板引擎的代码服务抽象类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class BaseTemplateCodeService implements CodeService {
  protected final TemplateService templateService;
  protected final ContextProcessor processor;

  @Override
  public List<Code> generate(@NonNull DatabaseTable table, @NonNull TemplateGroup templateGroup) {
    List<Template> templates = getTemplates(templateGroup);
    Assert.notEmpty(templates, "未找到模板内容");
    Map<String, Object> context = processor.process(table);
    return templates.stream().map(template -> doGenerate(table, template, context)).toList();
  }

  protected List<Template> getTemplates(TemplateGroup templateGroup) {
    return templateService.list(Map.of("groupId", templateGroup.getId()));
  }

  protected abstract Code doGenerate(DatabaseTable table, Template template, Map<String, Object> context);
}
