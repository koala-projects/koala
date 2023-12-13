package cn.koala.template.service;

import cn.koala.data.service.CrudService;
import cn.koala.template.domain.Template;
import cn.koala.template.domain.TemplateGroup;

import java.util.List;

/**
 * 模板组服务接口
 *
 * @author Houtaroy
 */
public interface TemplateGroupService extends CrudService<TemplateGroup, Long> {

  List<Template> listTemplate(Long id);
}
