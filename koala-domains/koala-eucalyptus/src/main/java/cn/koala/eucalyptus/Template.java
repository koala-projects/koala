package cn.koala.eucalyptus;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface Template {
  /**
   * 获取模板代码
   *
   * @return 模板代码
   */
  String getCode();

  /**
   * 获取模板名称
   *
   * @return 模板名称
   */
  String getName();

  /**
   * 获取生成器列表
   *
   * @return 生成器列表
   */
  List<Generator> getGenerators();
}
