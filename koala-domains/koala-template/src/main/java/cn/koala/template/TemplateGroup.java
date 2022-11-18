package cn.koala.template;

import cn.koala.persistence.Deletable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Systemic;

import java.util.List;

/**
 * 模板组
 *
 * @author Houtaroy
 */
public interface TemplateGroup extends Idable<String>, Systemic, Deletable {
  /**
   * 获取模板组名称
   *
   * @return 模板组名称
   */
  String getName();

  /**
   * 获取模板列表
   *
   * @return 模板列表
   */
  List<? extends Template> getTemplates();
}
