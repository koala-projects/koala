package cn.koala.template.services;

import cn.koala.mybatis.BaseService;
import cn.koala.template.Template;
import cn.koala.template.repositories.TemplateRepository;

/**
 * 模板服务持久化实现类
 *
 * @author Houtaroy
 */
public class TemplateServiceImpl extends BaseService<Template, Long> implements TemplateService {

  public TemplateServiceImpl(TemplateRepository repository) {
    super(repository, (entity) -> null);
  }
}
