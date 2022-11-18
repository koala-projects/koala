package cn.koala.template.mybatis;

import cn.koala.mybatis.AbstractSmartService;
import cn.koala.mybatis.IdGenerator;
import cn.koala.mybatis.UUIDGenerator;
import cn.koala.template.Template;
import cn.koala.template.TemplateService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 模板服务, MyBatis实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateServiceImpl extends AbstractSmartService<String, Template> implements TemplateService {
  protected final IdGenerator<Template, String> idGenerator = new UUIDGenerator<>();
  protected final TemplateRepository repository;
}
