package cn.koala.sensitive.autoconfigure;

import cn.koala.sensitive.SensitiveWordFilter;
import cn.koala.sensitive.SensitiveWordService;
import cn.koala.sensitive.apis.SensitiveWordApi;
import cn.koala.sensitive.apis.SensitiveWordApiImpl;
import cn.koala.sensitive.support.CompositeSensitiveWordFilter;
import cn.koala.sensitive.support.FileSensitiveWordService;
import cn.koala.sensitive.support.InMemorySensitiveWordService;
import cn.koala.sensitive.support.ToolGoodSensitiveWordFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

/**
 * 敏感词自动配置类
 *
 * @author Houtaroy
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SensitiveWordProperties.class)
public class SensitiveWordAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "koala.sensitive-word", name = "file")
  public SensitiveWordService fileSensitiveWordService(SensitiveWordProperties properties) throws IOException {
    return new FileSensitiveWordService(properties.getFile());
  }

  @Bean
  @ConditionalOnMissingBean
  public SensitiveWordService inMemorySensitiveWordService() {
    return new InMemorySensitiveWordService();
  }

  @Bean
  @ConditionalOnMissingBean(name = "defaultSensitiveWordFilter")
  @ConditionalOnProperty(prefix = "koala.sensitive-word", name = "toolGood", havingValue = "true")
  public SensitiveWordFilter defaultSensitiveWordFilter(SensitiveWordService sensitiveWordService) {
    return new ToolGoodSensitiveWordFilter(sensitiveWordService);
  }

  @Bean
  @ConditionalOnMissingBean(name = "sensitiveWordFilter")
  public SensitiveWordFilter sensitiveWordFilter(List<SensitiveWordFilter> filters) {
    return new CompositeSensitiveWordFilter(filters);
  }

  @Bean
  @ConditionalOnMissingBean
  public SensitiveWordApi sensitiveWordApi(SensitiveWordFilter sensitiveWordFilter) {
    return new SensitiveWordApiImpl(sensitiveWordFilter);
  }
}
