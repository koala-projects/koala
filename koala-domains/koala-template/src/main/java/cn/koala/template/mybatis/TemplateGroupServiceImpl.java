package cn.koala.template.mybatis;

import cn.koala.mybatis.AbstractSmartService;
import cn.koala.mybatis.IdGenerator;
import cn.koala.mybatis.UUIDGenerator;
import cn.koala.template.TemplateGroup;
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
    entity.getTemplates().forEach(templateService::add);
  }

  @Override
  public void update(TemplateGroup entity) {
    Optional<TemplateGroup> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    getRepository().update(entity);
    persist.get().getTemplates().forEach(templateService::delete);
    entity.getTemplates().forEach(templateService::add);
  }

  @Override
  public void delete(TemplateGroup entity) {
    Optional<TemplateGroup> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    getRepository().delete(persist.get());
    persist.get().getTemplates().forEach(templateService::delete);
  }
}
