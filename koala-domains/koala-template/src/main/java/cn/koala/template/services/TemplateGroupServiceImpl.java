package cn.koala.template.services;

import cn.koala.mybatis.BaseMyBatisService;
import cn.koala.template.TemplateGroup;
import cn.koala.template.repositories.TemplateGroupRepository;

/**
 * 模板组服务持久化实现类
 *
 * @author Houtaroy
 */
public class TemplateGroupServiceImpl extends BaseMyBatisService<TemplateGroup, Long> implements TemplateGroupService {

  public TemplateGroupServiceImpl(TemplateGroupRepository repository) {
    super(repository);
  }
}
