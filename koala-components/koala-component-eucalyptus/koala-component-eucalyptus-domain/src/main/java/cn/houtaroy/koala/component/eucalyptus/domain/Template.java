package cn.houtaroy.koala.component.eucalyptus.domain;

/**
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
   * 获取领域定义转换器
   *
   * @return 领域定义转换器
   */
  Converter getConverter();

  /**
   * 获取代码生成器
   *
   * @return 代码生成器
   */
  Generator getGenerator();
}
