package cn.koala.template.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.template.TemplateGroup;
import cn.koala.template.repositories.TemplateGroupRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 模板组服务持久化实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class TemplateGroupServiceImpl extends AbstractMyBatisService<TemplateGroup, Long> implements TemplateGroupService {

  protected final TemplateGroupRepository repository;
}
