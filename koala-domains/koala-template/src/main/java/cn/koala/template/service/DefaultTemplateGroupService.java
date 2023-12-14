package cn.koala.template.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.template.domain.TemplateGroup;
import cn.koala.template.repository.TemplateGroupRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

/**
 * 默认模板组服务
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultTemplateGroupService extends AbstractSmartService<Long, TemplateGroup, Long>
  implements TemplateGroupService {

  private final TemplateGroupRepository repository;
  private final AuditorAware<Long> auditorAware;
}
