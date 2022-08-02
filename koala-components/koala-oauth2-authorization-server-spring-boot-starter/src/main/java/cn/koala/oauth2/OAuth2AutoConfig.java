package cn.koala.oauth2;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 安全自动配置
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties({ProviderProperties.class, JwtTokenProperties.class})
@EnableWebSecurity
@Import({BeanAutoConfig.class, SwaggerAutoConfig.class})
@SuppressWarnings("PMD")
public class OAuth2AutoConfig {

  /**
   * 认证服务安全过滤链的Bean
   *
   * @param http                 http
   * @param authorizationService 认证服务
   * @return 认证服务安全过滤链
   * @throws Exception Exception
   */
  @Bean
  @Order(100)
  public SecurityFilterChain authorizationSecurityFilterChain(HttpSecurity http,
                                                              OAuth2AuthorizationService authorizationService)
    throws Exception {
    OAuth2AuthorizationServerConfigurer<HttpSecurity> configurer = new OAuth2AuthorizationServerConfigurer<>();
    configurer.tokenEndpoint(new OAuth2TokenEndpointConfigurerCustomizer());
    RequestMatcher endpointsMatcher = configurer.getEndpointsMatcher();
    http.requestMatcher(endpointsMatcher)
      .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
      .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
      .apply(configurer);
    SecurityFilterChain result = http.formLogin(Customizer.withDefaults()).build();
    OAuth2TokenGenerator<?> tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);
    http.authenticationProvider(new OAuth2PasswordAuthenticationProvider(
      http.getSharedObject(AuthenticationManager.class),
      authorizationService,
      tokenGenerator
    ));
    return result;
  }
}
