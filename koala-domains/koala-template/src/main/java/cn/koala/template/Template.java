package cn.koala.template;

/**
 * 模板
 *
 * @author Houtaroy
 */
public interface Template {
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
