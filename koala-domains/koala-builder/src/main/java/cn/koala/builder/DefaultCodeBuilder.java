package cn.koala.builder;

import cn.koala.jdbc.Table;
import cn.koala.template.Renderer;
import cn.koala.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 默认代码构建器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultCodeBuilder implements CodeBuilder {
  protected final DomainConverterService domainConverterService;
  protected final Renderer renderer;

  @Override
  public List<CodeBuilderResult> build(@NonNull Table table, @NonNull CodeTemplateGroup codeTemplateGroup,
                                       Map<String, Object> parameters) {
    Optional<DomainConverter> converter = domainConverterService.load(codeTemplateGroup.getDomainConverterId());
    Assert.isTrue(converter.isPresent(), "领域转换器不存在");
    Map<String, Object> context = Map.of(
      "table", table,
      "domain", converter.get().convert(table),
      "parameters", parameters
    );
    return codeTemplateGroup.getTemplates().stream().map(template -> buildOne(template, context)).toList();
  }

  /**
   * 执行构建
   *
   * @param template 代码模板
   * @param context  数据
   * @return 渲染结果
   */
  protected CodeBuilderResult buildOne(@NonNull Template template, Map<String, Object> context) {
    return CodeBuilderResult.builder()
      .name(renderer.renderOrDefault(template.getName(), context, template.getName()))
      .content(renderer.renderOrDefault(template.getContent(), context, "代码生成失败"))
      .build();
  }
}
