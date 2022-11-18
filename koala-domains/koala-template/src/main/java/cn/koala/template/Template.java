package cn.koala.template;

import cn.koala.persistence.Idable;

/**
 * 模板
 *
 * @author Houtaroy
 */
public interface Template extends Idable<String> {
  /**
   * 获取模板名称
   *
   * @return 模板名称
   */
  String getName();

  /**
   * 获取模板内容
   *
   * @return 模板内容
   */
  String getContent();
}
