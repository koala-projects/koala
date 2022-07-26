package cn.koala.office;

import org.jodconverter.core.DocumentConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * office自动配置
 *
 * @author Houtaroy
 */
@Configuration
public class OfficeAutoConfig {

  /**
   * office转换器的bean
   *
   * @param documentConverter jod文档转换器
   * @return office转换器
   */
  @Bean
  @ConditionalOnMissingBean
  public OfficeConverter officeConverter(DocumentConverter documentConverter) {
    return new JodOfficeConverter(documentConverter);
  }
}
