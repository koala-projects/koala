package cn.koala.template.support;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.template.Template;
import cn.koala.template.TemplateGroup;
import cn.koala.template.TemplateGroupService;
import cn.koala.template.TemplateService;
import cn.koala.template.repository.TemplateGroupRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 模板组服务持久化实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultTemplateGroupService extends AbstractMyBatisService<TemplateGroup, Long>
  implements TemplateGroupService {

  protected final TemplateGroupRepository repository;
  protected final TemplateService templateService;

  @Override
  public List<Template> listTemplate(Long id) {
    return templateService.list(Map.of("groupId", id));
  }
}
