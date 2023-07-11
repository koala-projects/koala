package cn.koala.template.support;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.template.TemplateGroup;
import cn.koala.template.TemplateGroupService;
import cn.koala.template.repository.TemplateGroupRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 模板组服务持久化实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultTemplateGroupService extends AbstractMyBatisService<TemplateGroup, Long> implements TemplateGroupService {

  protected final TemplateGroupRepository repository;
}
