package cn.koala.builder.mybatis;

import cn.koala.builder.CodeTemplateGroup;
import cn.koala.builder.CodeTemplateGroupService;
import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.MetadataEntity;
import cn.koala.datamodel.PropertyEntity;
import cn.koala.datamodel.PropertyService;
import cn.koala.mybatis.AbstractSmartService;
import cn.koala.mybatis.IdGenerator;
import cn.koala.mybatis.UUIDGenerator;
import cn.koala.template.TemplateEntity;
import cn.koala.template.TemplateGroup;
import cn.koala.template.TemplateGroupEntity;
import cn.koala.template.TemplateService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

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
}
