package cn.houtaroy.koala.component.eucalyptus;

import java.util.List;

/**
 * @param delegates 委派配置
 * @author Houtaroy
 */
public record TemplateConfigurerComposite(List<TemplateConfigurer> delegates) implements TemplateConfigurer {
  @Override
  public void configure(TemplateService templateService) {
    for (TemplateConfigurer delegate : delegates) {
      delegate.configure(templateService);
    }
  }
}
