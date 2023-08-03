package cn.koala.resource.autoconfigure;

import cn.koala.resource.ResourceServerControllerAdvice;
import cn.koala.resource.builder.ResourceServerSecurityFilterChainPostProcessor;
import cn.koala.resource.builder.support.OAuth2ResourceServerPostProcessor;
import cn.koala.resource.builder.support.PermitAllPostProcessor;
import cn.koala.resource.introspection.AuthorityExtractor;
import cn.koala.resource.introspection.DefaultOpaqueTokenIntrospector;
import cn.koala.resource.introspection.support.UserAuthenticationAuthorityExtractor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.util.List;

/**
 * 资源服自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(ResourceServerProperties.class)
@EnableWebSecurity
@EnableMethodSecurity
@Import(OpenApiAutoConfiguration.class)
public class ResourceServerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "oauth2ResourceServerPostProcessor")
  public ResourceServerSecurityFilterChainPostProcessor oauth2ResourceServerPostProcessor() {
    return new OAuth2ResourceServerPostProcessor();
  }

  @Bean
  @Order(3800)
  public ResourceServerSecurityFilterChainPostProcessor permitAllPostProcessor(ResourceServerProperties properties) {
    return new PermitAllPostProcessor(properties.getPermitAllPatterns());
  }

  @Bean
  @ConditionalOnBean(UserDetailsService.class)
  @ConditionalOnMissingBean
  public AuthorityExtractor userAuthenticationAuthorityExtractor(UserDetailsService userDetailsService) {
    return new UserAuthenticationAuthorityExtractor(userDetailsService);
  }

  @Bean
  @ConditionalOnMissingBean(name = "opaqueTokenIntrospector")
  public OpaqueTokenIntrospector opaqueTokenIntrospector(OAuth2ResourceServerProperties properties,
                                                         List<AuthorityExtractor> authorityExtractors) {
    return new DefaultOpaqueTokenIntrospector(
      properties.getOpaquetoken().getIntrospectionUri(),
      properties.getOpaquetoken().getClientId(),
      properties.getOpaquetoken().getClientSecret(),
      authorityExtractors
    );
  }

  @Bean
  public ResourceServerControllerAdvice resourceServerControllerAdvice() {
    return new ResourceServerControllerAdvice();
  }
}
