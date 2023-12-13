package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenContext;
import cn.koala.codegen.CodeGenResult;
import cn.koala.codegen.CodeGenerator;
import cn.koala.codegen.CompositeCodeGenContextProcessor;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.template.domain.Template;
import cn.koala.template.domain.TemplateEntity;
import cn.koala.template.domain.TemplateRenderer;

/**
 * 模板引擎代码生成器
 *
 * @author Houtaroy
 */
public class TemplateEngineCodeGenerator implements CodeGenerator {

  private final TemplateRenderer renderer;
  private final CompositeCodeGenContextProcessor processor;

  public TemplateEngineCodeGenerator(TemplateRenderer renderer, CompositeCodeGenContextProcessor processor) {
    this.renderer = renderer;
    this.processor = processor;
  }

  @Override
  public CodeGenResult generate(DatabaseTable table, Template template) {
    CodeGenContext context = processor.processAll(table);
    return SimpleCodeGenResult.builder()
      .pathname(renderer.render(TemplateEntity.builder().content(template.getName()).build(), context))
      .content(renderer.render(template, context))
      .build();
  }
}
