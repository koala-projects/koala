package cn.koala.template.mybatis;

import cn.koala.mybatis.AbstractSmartService;
import cn.koala.mybatis.IdGenerator;
import cn.koala.mybatis.UUIDGenerator;
import cn.koala.template.TemplateEntity;
import cn.koala.template.TemplateGroup;
import cn.koala.template.TemplateGroupEntity;
import cn.koala.template.TemplateGroupService;
import cn.koala.template.TemplateService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * 模板组服务, MyBatis实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateGroupServiceImpl extends AbstractSmartService<String, TemplateGroup>
  implements TemplateGroupService {
  protected final IdGenerator<TemplateGroup, String> idGenerator = new UUIDGenerator<>();
  protected final TemplateGroupRepository repository;
  protected final TemplateService templateService;

  @Override
  public void add(TemplateGroup entity) {
    super.add(entity);
    addTemplates(entity);
  }

  @Override
  public void update(TemplateGroup entity) {
    Optional<TemplateGroup> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    getRepository().update(entity);
    persist.get().getTemplates().forEach(templateService::delete);
    addTemplates(entity);
  }

  @Override
  public void delete(TemplateGroup entity) {
    Optional<TemplateGroup> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    getRepository().delete(persist.get());
    persist.get().getTemplates().forEach(templateService::delete);
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
