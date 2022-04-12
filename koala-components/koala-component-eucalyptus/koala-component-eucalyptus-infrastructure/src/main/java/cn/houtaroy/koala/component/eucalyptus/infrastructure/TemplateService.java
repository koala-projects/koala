package cn.houtaroy.koala.component.eucalyptus.infrastructure;

import cn.houtaroy.koala.component.eucalyptus.domain.Template;

import java.util.List;
import java.util.Optional;

/**
 * @author Houtaroy
 */
public interface TemplateService {

  /**
   * 查询模板列表
   *
   * @param name 模板名称
   * @return 模板列表
   */
  List<Template> list(String name);

  /**
   * 根据名称查询模板
   *
   * @param name 模板名称
   * @return 模板
   */
  Optional<Template> loadByName(String name);

  /**
   * 新增模板
   *
   * @param template 模板
   * @return 模板
   */
  Template add(Template template);

  /**
   * 删除模板
   *
   * @param name 模板名称
   */
  void delete(String name);
}
