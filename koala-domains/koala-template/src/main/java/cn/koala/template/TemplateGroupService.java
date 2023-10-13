package cn.koala.template;

import cn.koala.persist.CrudService;

import java.util.List;

/**
 * 模板组服务接口
 *
 * @author Houtaroy
 */
public interface TemplateGroupService extends CrudService<TemplateGroup, Long> {

  List<Template> listTemplate(Long id);
}
