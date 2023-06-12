package cn.koala.template.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.template.Template;
import cn.koala.template.repositories.TemplateRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 模板服务持久化实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class TemplateServiceImpl extends AbstractMyBatisService<Template, Long> implements TemplateService {

  protected final TemplateRepository repository;
}
