package cn.koala.builder;

import cn.koala.datamodel.Metadata;
import cn.koala.template.TemplateGroup;

/**
 * 代码模板组
 *
 * @author Houtaroy
 */
public interface CodeTemplateGroup extends TemplateGroup, Metadata {
  /**
   * 获取领域转换器ID
   *
   * @return 领域转换器ID
   */
  String getDomainConverterId();
}
