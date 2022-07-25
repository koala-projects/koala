package cn.koala.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Bean自动配置类
 *
 * @author Houtaroy
 */
public class BeanAutoConfig {

  /**
   * OAuth2认证确认服务配置
   *
   * @param jdbcTemplate               jdbc模板
   * @param registeredClientRepository 注册客户端数据访问接口
   * @return OAuth2认证确认服务实例
   */
  @Bean
  @ConditionalOnMissingBean
  public OAuth2AuthorizationConsentService authorizationConsentService(
    JdbcTemplate jdbcTemplate,
    RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * OAuth2授权服务配置
   *
   * @param jdbcTemplate               jdbc模板
   * @param registeredClientRepository 注册客户端数据访问接口
   * @return OAuth2授权服务实例
   */
  @Bean
  @ConditionalOnMissingBean
  public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
                                                         RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * 注册客户端数据访问接口
   *
   * @param jdbcTemplate jdbcTemplate
   * @return 注册客户端数据访问接口
   */
  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcRegisteredClientRepository(jdbcTemplate);
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
   * JWK集合配置
   *
   * @param properties jwt令牌配置
   * @return JWK集合实例
   */
  @Bean
  @ConditionalOnMissingBean
  public JWKSource<SecurityContext> jwkSource(JwtTokenProperties properties)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    JWKSet jwkSet = new JWKSet(
      Jwks.generateRsa(properties.getKeyID(), properties.getPublicKey(), properties.getPrivateKey())
    );
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  /**
   * JWT令牌解码器配置
   *
   * @param jwkSource JWK源
   * @return JWT令牌解码器
   */
  @Bean
  @ConditionalOnMissingBean
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }
}
