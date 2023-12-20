package cn.koala.template.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.template.domain.Template;
import cn.koala.template.repository.TemplateRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 默认模板服务类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultTemplateService extends AbstractSmartService<Template, Long> implements TemplateService {

  private final TemplateRepository repository;
}
