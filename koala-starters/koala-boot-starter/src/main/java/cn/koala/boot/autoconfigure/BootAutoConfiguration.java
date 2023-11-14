package cn.koala.boot.autoconfigure;

import cn.koala.boot.config.ApplicationRunnerConfigRegistry;
import cn.koala.boot.config.InMemoryApplicationRunnerConfigRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 启动自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(BootProperties.class)
public class BootAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ApplicationRunnerConfigRegistry applicationRunnerConfigRegistry(BootProperties properties) {
    return new InMemoryApplicationRunnerConfigRegistry(properties.getRunners());
  }
}
