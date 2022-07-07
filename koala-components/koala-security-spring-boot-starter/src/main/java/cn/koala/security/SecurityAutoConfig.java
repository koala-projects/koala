package cn.koala.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

/**
 * 安全自动配置
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(ProviderProperties.class)
@Import({JwtAutoConfig.class, SwaggerConfig.class})
public class SecurityAutoConfig {

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
   * 认证服务安全过滤链的Bean
   *
   * @param http                 http
   * @param authorizationService 认证服务
   * @param customizers          Jwt上下文定制器列表
   * @param properties           提供者参数
   * @return 认证服务安全过滤链
   * @throws Exception Exception
   */
  @Bean
  @Order(1)
  public SecurityFilterChain authorizationSecurityFilterChain(HttpSecurity http,
                                                              OAuth2AuthorizationService authorizationService,
                                                              List<JwtEncodingContextCustomizer> customizers,
                                                              ProviderProperties properties)
    throws Exception {
    OAuth2AuthorizationServerConfigurer<HttpSecurity> configurer = new OAuth2AuthorizationServerConfigurer<>();
    configurer.tokenEndpoint(
      endpoint -> endpoint.accessTokenRequestConverter(new OAuth2ResourceOwnerPasswordAuthenticationConverter())
    );
    RequestMatcher endpointsMatcher = configurer.getEndpointsMatcher();
    http.requestMatcher(endpointsMatcher)
      .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
      .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
      .apply(configurer);
    SecurityFilterChain result = http.build();
    http.authenticationProvider(new OAuth2ResourceOwnerPasswordAuthenticationProvider(
      http.getSharedObject(AuthenticationManager.class),
      http.getSharedObject(JwtEncoder.class),
      new DelegatingJwtEncodingContextCustomizer(customizers),
      authorizationService,
      properties
    ));
    return result;
  }

  /**
   * 授权服务器默认安全过滤器链配置
   *
   * @param http HttpSecurity对象
   * @return 安全过滤器链实例
   * @throws Exception 异常
   */
  @Bean
  @Order(2)
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthorityPrefix("");
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return http.authorizeRequests().antMatchers("/**/*swagger*/**", "/**/api-doc*").permitAll()
      .and().authorizeRequests().anyRequest().authenticated()
      .and()
      .oauth2ResourceServer(oauth2 ->
        oauth2.jwt(jwt -> jwt.decoder(jwtDecoder).jwtAuthenticationConverter(converter))
      ).build();
  }

  /**
   * 提供者配置的bean
   *
   * @return 提供者配置
   */
  @Bean
  public ProviderSettings providerSettings(ProviderProperties properties) {
    return ProviderSettings.builder().issuer(properties.getIssuer()).build();
  }

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
}
