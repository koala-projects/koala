package cn.koala.codegen.service;

import cn.koala.codegen.context.CodeGenContext;
import cn.koala.codegen.context.CodeGenContextProcessor;
import cn.koala.codegen.context.DefaultCodeGenContext;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.template.domain.Template;
import cn.koala.template.domain.TemplateEntity;
import cn.koala.template.domain.TemplateRenderer;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultCodeGenService implements CodeGenService {

  private final List<CodeGenContextProcessor> contextProcessors;
  private final TemplateRenderer templateRenderer;

  @Override
  public CodeGenResult generate(DatabaseTable table, Template template) {
    CodeGenContext context = new DefaultCodeGenContext(table);
    for (CodeGenContextProcessor contextProcessor : contextProcessors) {
      context = contextProcessor.process(context);
    }
    var templateContext = context.toMap();
    return CodeGenResult.builder()
      .filename(templateRenderer.render(TemplateEntity.builder().content(template.getName()).build(), templateContext))
      .content(templateRenderer.render(template, templateContext))
      .build();
  }
}
