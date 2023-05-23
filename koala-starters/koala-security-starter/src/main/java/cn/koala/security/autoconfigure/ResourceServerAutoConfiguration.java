package cn.koala.security.autoconfigure;

import cn.koala.security.SecurityProperties;
import cn.koala.security.builder.ResourceServerBuilder;
import cn.koala.security.builder.processor.ResourceServerProcessor;
import cn.koala.security.builder.processor.support.CustomPermitAllProcessor;
import cn.koala.security.builder.processor.support.LoginPermitAllProcessor;
import cn.koala.security.builder.processor.support.LogoutSuccessHandlerProcessor;
import cn.koala.security.builder.processor.support.OAuth2ResourceServerProcessor;
import cn.koala.security.builder.processor.support.OpenApiProcessor;
import cn.koala.security.builder.support.DefaultResourceServerBuilder;
import cn.koala.security.repositories.UserDetailsRepository;
import cn.koala.security.services.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * 默认安全自动配置
 *
 * @author Houtaroy
 */
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class ResourceServerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "resourceServerProcessor")
  public ResourceServerProcessor resourceServerProcessor() {
    return new OAuth2ResourceServerProcessor();
  }

  @Bean
  @ConditionalOnMissingBean(name = "logoutSuccessHandlerPostProcessor")
  public ResourceServerProcessor logoutSuccessHandlerPostProcessor() {
    return new LogoutSuccessHandlerProcessor();
  }

  @Bean
  @ConditionalOnMissingBean(name = "loginPermitAllProcessor")
  public ResourceServerProcessor loginPermitAllProcessor() {
    return new LoginPermitAllProcessor();
  }

  @Bean
  @ConditionalOnMissingBean(name = "customPermitAllProcessor")
  public ResourceServerProcessor customPermitAllProcessor(SecurityProperties properties) {
    return new CustomPermitAllProcessor(properties);
  }

  @Bean
  @ConditionalOnClass(name = "org.springdoc.webmvc.ui.SwaggerConfig")
  @ConditionalOnMissingBean(name = "openApiProcessor")
  public ResourceServerProcessor openApiProcessor() {
    return new OpenApiProcessor();
  }

  @Bean
  @ConditionalOnMissingBean
  public ResourceServerBuilder resourceServerBuilder(List<ResourceServerProcessor> processors) {
    return new DefaultResourceServerBuilder(processors);
  }

  @Bean
  @ConditionalOnMissingBean(name = "resourceServerSecurityFilterChain")
  public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http, ResourceServerBuilder builder)
    throws Exception {
    return builder.build(http);
  }

  @Bean
  public UserDetailsService userDetailsService(UserDetailsRepository userDetailsRepository) {
    return new UserDetailsServiceImpl(userDetailsRepository);
  }
}
