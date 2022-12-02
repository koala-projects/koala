package cn.koala.builder.mybatis;

import cn.koala.builder.CodeBuildResult;
import cn.koala.builder.CodeTemplateGroup;
import cn.koala.builder.CodeTemplateGroupService;
import cn.koala.builder.DomainConverter;
import cn.koala.builder.DomainConverterService;
import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.MetadataEntity;
import cn.koala.datamodel.PropertyEntity;
import cn.koala.datamodel.PropertyService;
import cn.koala.jdbc.Table;
import cn.koala.mybatis.AbstractSmartService;
import cn.koala.mybatis.IdGenerator;
import cn.koala.mybatis.UUIDGenerator;
import cn.koala.template.Renderer;
import cn.koala.template.Template;
import cn.koala.template.TemplateEntity;
import cn.koala.template.TemplateGroup;
import cn.koala.template.TemplateGroupEntity;
import cn.koala.template.TemplateService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class CodeTemplateGroupServiceImpl extends AbstractSmartService<String, CodeTemplateGroup>
  implements CodeTemplateGroupService {
  protected final IdGenerator<CodeTemplateGroup, String> idGenerator = new UUIDGenerator<>();
  protected final CodeTemplateGroupRepository repository;
  protected final PropertyService propertyService;
  protected final TemplateService templateService;
  protected final DomainConverterService domainConverterService;
  protected final Renderer renderer;

  @Override
  public void add(CodeTemplateGroup entity) {
    super.add(entity);
    addProperties(entity);
    addTemplates(entity);
  }

  @Override
  public void update(CodeTemplateGroup entity) {
    Optional<CodeTemplateGroup> persist = repository.findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    repository.update(entity);
    persist.get().getProperties().forEach(propertyService::delete);
    addProperties(entity);
    persist.get().getTemplates().forEach(templateService::delete);
    addTemplates(entity);
  }

  @Override
  public void delete(CodeTemplateGroup entity) {
    Optional<CodeTemplateGroup> persist = repository.findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    repository.delete(persist.get());
    persist.get().getProperties().forEach(propertyService::delete);
    persist.get().getTemplates().forEach(templateService::delete);
  }

  /**
   * 增加属性列表
   *
   * @param entity 元数据实体
   */
  protected void addProperties(Metadata entity) {
    entity.getProperties().forEach(property -> {
      if (property instanceof PropertyEntity propertyEntity) {
        propertyEntity.setMetadata(MetadataEntity.builder().id(entity.getId()).build());
      }
      propertyService.add(property);
    });
  }

  /**
   * 添加模板列表
   *
   * @param group 模板组对象
   */
  protected void addTemplates(TemplateGroup group) {
    group.getTemplates().forEach(template -> {
      if (template instanceof TemplateEntity entity) {
        entity.setGroup(TemplateGroupEntity.builder().id(group.getId()).build());
      }
      templateService.add(template);
    });
  }

  @Override
  public List<CodeBuildResult> build(String id, Table table, Map<String, Object> properties) {
    Optional<CodeTemplateGroup> group = load(id);
    Assert.isTrue(group.isPresent(), "代码模板组不存在");
    Optional<DomainConverter> converter = domainConverterService.load(group.get().getDomainConverterId());
    Assert.isTrue(converter.isPresent(), "领域转换器不存在");
    Map<String, Object> context = Map.of(
      "table", table,
      "domain", converter.get().convert(table),
      "properties", properties
    );
    return group.get().getTemplates().stream().map(template -> build(template, context)).toList();
  }

  /**
   * 构建
   *
   * @param template 代码模板
   * @param context  参数
   * @return 构建结果
   */
  protected CodeBuildResult build(@NonNull Template template, Map<String, Object> context) {
    return CodeBuildResult.builder()
      .name(renderer.renderOrDefault(template.getName(), context, template.getName()))
      .content(renderer.renderOrDefault(template.getContent(), context, "代码生成失败"))
      .build();
  }
}
