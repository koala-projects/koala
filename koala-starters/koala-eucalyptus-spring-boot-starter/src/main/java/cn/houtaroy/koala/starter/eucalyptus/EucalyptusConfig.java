package cn.houtaroy.koala.starter.eucalyptus;

import cn.houtaroy.koala.component.eucalyptus.Converter;
import cn.houtaroy.koala.component.eucalyptus.InMemoryTemplateServiceImpl;
import cn.houtaroy.koala.component.eucalyptus.TemplateConfigurer;
import cn.houtaroy.koala.component.eucalyptus.TemplateConfigurerComposite;
import cn.houtaroy.koala.component.eucalyptus.TemplateService;
import cn.houtaroy.koala.component.jdbc.DatabaseService;
import cn.houtaroy.koala.component.jdbc.JdbcDatabaseService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Houtaroy
 */
@Configuration
public class EucalyptusConfig {
  /**
   * 数据库服务的bean
   *
   * @return 数据库服务
   */
  @Bean
  @ConditionalOnMissingBean
  public DatabaseService databaseService() {
    return new JdbcDatabaseService();
  }

  /**
   * 转换器的bean
   *
   * @return 转换器
   */
  @Bean
  @ConditionalOnMissingBean
  public Converter converter() {
    return new Converter();
  }

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

  /**
   * 模板服务配置集合的bean
   *
   * @param configurers     模板服务配置器
   * @param templateService 模板服务
   * @return 模板服务配置集合
   */
  @Bean
  public TemplateConfigurerComposite templateConfigurerComposite(List<TemplateConfigurer> configurers,
                                                                 TemplateService templateService) {
    TemplateConfigurerComposite result = new TemplateConfigurerComposite(configurers);
    result.configure(templateService);
    return result;
  }
}
