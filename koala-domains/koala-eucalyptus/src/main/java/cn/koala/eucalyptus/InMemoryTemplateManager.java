package cn.koala.eucalyptus;

import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 内存模板管理器
 *
 * @author Houtaroy
 */
public class InMemoryTemplateManager implements TemplateManager {
  private final List<Template> templates;
  private final Map<String, Template> templateMap;

  /**
   * 构造函数
   *
   * @param templates 模板列表
   */
  public InMemoryTemplateManager(@NonNull List<Template> templates) {
    Objects.requireNonNull(templates);
    this.templates = templates;
    this.templateMap = templates.stream().collect(Collectors.toMap(Template::getCode, t -> t));
  }

  @Override
  public List<Template> getTemplates() {
    return templates;
  }

  @Override
  public Map<String, String> process(String code, DomainContext context) {
    return Optional.ofNullable(templateMap.get(code)).map(t -> t.process(context)).orElse(new HashMap<>(0));
  }
}
