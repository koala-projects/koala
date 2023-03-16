package cn.koala.template;

import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.StateModel;

/**
 * 模板接口
 *
 * @author Houtaroy
 */
public interface Template extends IdModel<Long>, StateModel {
  String getName();

  String getContent();

  String getRemark();

  Long getGroupId();
}
