package cn.koala.admin.client.autoconfigure;

import cn.koala.security.builder.processor.ResourceServerProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Admin Client自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class AdminClientAutoConfiguration {

  @Bean
  @ConditionalOnClass(name = "cn.koala.security.autoconfigure.ResourceServerAutoConfiguration")
  @ConditionalOnMissingBean(name = "actuatorProcessor")
  public ResourceServerProcessor actuatorProcessor() {
    return new ActuatorProcessor();
  }
}
