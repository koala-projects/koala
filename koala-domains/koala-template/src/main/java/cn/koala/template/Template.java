package cn.koala.template;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Stateful;

/**
 * 模板接口
 *
 * @author Houtaroy
 */
public interface Template extends Persistable<Long>, Stateful {
  String getName();

  String getContent();

  String getRemark();

  Long getGroupId();
}
