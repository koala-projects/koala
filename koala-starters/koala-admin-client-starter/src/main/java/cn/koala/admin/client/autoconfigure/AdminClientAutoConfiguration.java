package cn.koala.admin.client.autoconfigure;

import cn.koala.resource.builder.ResourceServerSecurityFilterChainPostProcessor;
import cn.koala.resource.builder.support.PermitAllPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Spring Boot Admin Client自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class AdminClientAutoConfiguration {

  @Bean
  @Order(3400)
  @ConditionalOnMissingBean(name = "actuatorProcessor")
  public ResourceServerSecurityFilterChainPostProcessor actuatorProcessor() {
    return new PermitAllPostProcessor("/actuator/**");
  }
}
