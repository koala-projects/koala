package cn.koala.template;

import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.StateModel;

/**
 * 模板组接口
 *
 * @author Houtaroy
 */
public interface TemplateGroup extends IdModel<Long>, StateModel {
  String getName();

  String getRemark();
}
