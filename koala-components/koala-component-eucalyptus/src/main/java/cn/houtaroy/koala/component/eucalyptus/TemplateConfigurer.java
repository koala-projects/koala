package cn.houtaroy.koala.component.eucalyptus;

/**
 * @author Houtaroy
 */
public interface TemplateConfigurer {
  /**
   * 配置模板服务
   *
   * @param templateService 模板服务
   */
  void configure(TemplateService templateService);
}
