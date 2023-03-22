package cn.koala.template;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Stateful;

/**
 * 模板组接口
 *
 * @author Houtaroy
 */
public interface TemplateGroup extends Persistable<Long>, Stateful {
  String getName();

  String getRemark();
}
