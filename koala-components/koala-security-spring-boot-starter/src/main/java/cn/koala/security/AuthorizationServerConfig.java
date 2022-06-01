package cn.koala.security;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 授权服务配置
 *
 * @author Houtaroy
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {
  /**
   * 认证授权服务的bean
   *
   * @param jdbcTemplate               jdbcTemplate
   * @param registeredClientRepository 注册客户端存储库类
   * @return 认证授权服务
   */
  @Bean
  public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
                                                                       RegisteredClientRepository
                                                                         registeredClientRepository) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * 认证服务安全过滤链的bean
   *
   * @param http HttpSecurity
   * @return 认证服务安全过滤链
   * @throws Exception 异常
   */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
      new OAuth2AuthorizationServerConfigurer<>();
    authorizationServerConfigurer
      .tokenEndpoint(tokenEndpoint ->
        tokenEndpoint
          .accessTokenRequestConverter(new OAuth2ResourceOwnerPasswordAuthenticationConverter())
      );
    RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
    http.requestMatcher(endpointsMatcher)
      .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
      .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
      .apply(authorizationServerConfigurer);
    SecurityFilterChain result = http.formLogin(Customizer.withDefaults()).build();
    http.authenticationProvider(new OAuth2ResourceOwnerPasswordAuthenticationProvider(
      http.getSharedObject(AuthenticationManager.class),
      http.getSharedObject(JwtEncoder.class),
      http.getSharedObject(OAuth2AuthorizationService.class)
    ));
    return result;
  }

  /**
   * 认证服务的bean
   *
   * @param jdbcTemplate               jdbcTemplate
   * @param registeredClientRepository 注册客户端存储库类
   * @return 认证服务
   */
  @Bean
  public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
                                                         RegisteredClientRepository registeredClientRepository) {
    return new KoalaJdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * jwt解码器的bean
   *
   * @param jwkSource jwkSource
   * @return jwt解码器
   */
  @Bean
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }

  /**
   * jwkSource的bean
   *
   * @return jwkSource
   */
  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    return JwkUtil.jwkSource();
  }

  /**
   * 提供者配置的bean
   *
   * @return 提供者配置
   */
  @Bean
  public ProviderSettings providerSettings() {
    return ProviderSettings.builder().issuer("http://127.0.0.1:9999").build();
  }

  /**
   * 注册客户端存储库的bean
   *
   * @param jdbcTemplate jdbcTemplate
   * @return 注册客户端存储库
   */
  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcRegisteredClientRepository(jdbcTemplate);
  }
}
