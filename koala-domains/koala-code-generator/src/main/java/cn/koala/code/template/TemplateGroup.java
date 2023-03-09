package cn.koala.code.template;

import java.util.List;

/**
 * 模板组接口
 *
 * @author Houtaroy
 */
public interface TemplateGroup {
  String getName();

  <T extends Template> List<T> getTemplates();
}
