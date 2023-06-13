package cn.koala.admin.client.autoconfigure;

import cn.koala.security.authentication.builder.processor.ResourceServerProcessor;
import cn.koala.security.authentication.builder.processor.support.PermitAllProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
  @Order(2100)
  @ConditionalOnClass(name = "cn.koala.security.autoconfigure.ResourceServerAutoConfiguration")
  @ConditionalOnMissingBean(name = "actuatorProcessor")
  public ResourceServerProcessor actuatorProcessor() {
    return new PermitAllProcessor("/actuator/**");
  }
}
