package cn.houtaroy.koala.starter.eucalyptus;

import cn.houtaroy.koala.component.eucalyptus.infrastructure.InMemoryTemplateServiceImpl;
import cn.houtaroy.koala.component.eucalyptus.infrastructure.TemplateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class EucalyptusConfig {

  /**
   * 模板服务的bean
   *
   * @return 模板服务
   */
  @Bean
  @ConditionalOnMissingBean
  public TemplateService templateService() {
    return new InMemoryTemplateServiceImpl();
  }
}
