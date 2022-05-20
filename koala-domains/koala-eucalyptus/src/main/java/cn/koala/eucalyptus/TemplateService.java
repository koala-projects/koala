package cn.koala.eucalyptus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
public interface TemplateService {
  /**
   * 查询模板列表
   *
   * @param params 查询条件
   * @return 模板列表
   */
  List<Template> list(Map<String, Object> params);

  /**
   * 根据代码查询模板
   *
   * @param code 模板代码
   * @return 模板
   */
  Optional<Template> loadByCode(String code);

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
   * @param template 模板
   */
  void delete(Template template);
}
