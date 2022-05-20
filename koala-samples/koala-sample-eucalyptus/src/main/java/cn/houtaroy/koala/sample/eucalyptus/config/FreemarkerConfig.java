package cn.houtaroy.koala.sample.eucalyptus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @author Houtaroy
 */
@Configuration
public class FreemarkerConfig {

  /**
   * freemarker配置
   *
   * @return freemarker配置
   * @throws IOException IOException
   */
  @Bean
  public freemarker.template.Configuration configuration() throws IOException {
    freemarker.template.Configuration configuration =
      new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_31);
    configuration.setDefaultEncoding("UTF-8");
    configuration.setDirectoryForTemplateLoading(new File("D:/Temp/eucalyptus/templates"));
    return configuration;
  }
}
