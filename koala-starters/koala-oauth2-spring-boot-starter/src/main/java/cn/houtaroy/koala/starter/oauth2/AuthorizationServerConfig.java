package cn.houtaroy.koala.starter.oauth2;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Houtaroy
 */
@Configuration
public class AuthorizationServerConfig {

  /**
   * SecurityFilterChain的bean
   *
   * @param http http
   * @return SecurityFilterChain
   * @throws Exception 异常
   */
  @Bean
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    return http.formLogin(Customizer.withDefaults()).build();
  }

  /**
   * 注册客户端存储库类的bean
   *
   * @param jdbcTemplate jdbcTemplate
   * @return 注册客户端存储库类
   */
  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcRegisteredClientRepository(jdbcTemplate);
  }

  /**
   * OAuth2授权服务的bean
   *
   * @param jdbcTemplate               jdbcTemplate
   * @param registeredClientRepository 注册客户端存储库类
   * @return OAuth2授权服务
   */
  @Bean
  public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
                                                         RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * OAuth2授权服务同意服务的bean
   *
   * @param jdbcTemplate               jdbcTemplate
   * @param registeredClientRepository 注册客户端存储库类
   * @return OAuth2授权服务同意服务
   */
  @Bean
  public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
                                                                       RegisteredClientRepository
                                                                         registeredClientRepository) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * 提供服务设置的bean
   *
   * @return 提供服务设置
   */
  @Bean
  public ProviderSettings providerSettings() {
    return ProviderSettings.builder().issuer("http://localhost:9000").build();
  }

  /**
   * JWK源的bean
   *
   * @return JWK源
   */
  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    RSAKey rsaKey = Jwks.generateRsa();
    JWKSet jwkSet = new JWKSet(rsaKey);
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }
}
