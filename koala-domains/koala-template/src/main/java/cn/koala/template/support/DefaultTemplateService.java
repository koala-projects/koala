package cn.koala.template.support;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.template.Template;
import cn.koala.template.TemplateService;
import cn.koala.template.repository.TemplateRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 模板服务持久化实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultTemplateService extends AbstractMyBatisService<Template, Long> implements TemplateService {

  protected final TemplateRepository repository;
}
