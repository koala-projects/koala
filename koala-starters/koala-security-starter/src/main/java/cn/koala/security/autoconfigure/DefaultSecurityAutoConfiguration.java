package cn.koala.security.autoconfigure;

import cn.koala.security.authentication.builder.ResourceServerBuilder;
import cn.koala.security.authentication.builder.processor.ResourceServerProcessor;
import cn.koala.security.authentication.builder.processor.support.LogoutSuccessHandlerProcessor;
import cn.koala.security.authentication.builder.processor.support.OAuth2ResourceServerProcessor;
import cn.koala.security.authentication.builder.processor.support.PermitAllProcessor;
import cn.koala.security.authentication.builder.support.DefaultResourceServerBuilder;
import cn.koala.security.introspection.AuthorityOpaqueTokenIntrospector;
import cn.koala.security.userdetails.CacheableUserDetailsService;
import cn.koala.security.userdetails.repository.UserDetailsRepository;
import cn.koala.security.userdetails.support.DefaultUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
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
public class DefaultSecurityAutoConfiguration {

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
  public OpaqueTokenIntrospector introspector(OAuth2ResourceServerProperties properties, CacheableUserDetailsService userDetailsService) {
    OAuth2ResourceServerProperties.Opaquetoken opaquetoken = properties.getOpaquetoken();
    return new AuthorityOpaqueTokenIntrospector(
      opaquetoken.getIntrospectionUri(),
      opaquetoken.getClientId(),
      opaquetoken.getClientSecret(),
      userDetailsService
    );
  }

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
  @Order(1000)
  @ConditionalOnMissingBean(name = "loginPermitAllProcessor")
  public ResourceServerProcessor loginPermitAllProcessor() {
    return new PermitAllProcessor("/login/**");
  }

  @Bean
  @Order(3000)
  @ConditionalOnMissingBean(name = "customPermitAllProcessor")
  public ResourceServerProcessor customPermitAllProcessor(SecurityProperties properties) {
    return new PermitAllProcessor(properties.getPermitAllPatterns().toArray(new String[0]));
  }

  @Bean
  @Order(2000)
  @ConditionalOnClass(name = "org.springdoc.webmvc.ui.SwaggerConfig")
  @ConditionalOnMissingBean(name = "openApiProcessor")
  public ResourceServerProcessor openApiProcessor() {
    return new PermitAllProcessor("/swagger*/**", "/v3/api-docs/**");
  }

  @Bean
  public CacheableUserDetailsService userDetailsService(UserDetailsRepository userDetailsRepository) {
    return new DefaultUserDetailsService(userDetailsRepository);
  }
}
