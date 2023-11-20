package cn.koala.authorization.autoconfigure;

import cn.koala.authorization.builder.AuthorizationServerSecurityFilterChainPostProcessor;
import cn.koala.authorization.builder.support.OAuth2AuthorizationServerPostProcessor;
import cn.koala.authorization.builder.support.OAuth2ResourceOwnerPasswordPostProcessor;
import cn.koala.authorization.client.RegisteredClientRegister;
import cn.koala.authorization.client.RegisteredClientRegistrar;
import cn.koala.authorization.client.support.AdminRegisteredClientRegistrar;
import cn.koala.authorization.jackson.KoalaUserMixin;
import cn.koala.authorization.token.CompositeJwtOAuth2TokenCustomizer;
import cn.koala.authorization.token.JwtOAuth2TokenCustomizer;
import cn.koala.authorization.token.support.ClaimGrantTypeAccessTokenCustomizer;
import cn.koala.authorization.token.support.UserAuthenticationAccessTokenCustomizer;
import cn.koala.security.userdetails.KoalaUser;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * 认证授权服务自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(AuthorizationServerProperties.class)
@Import(DefaultSecurityAutoConfiguration.class)
@MapperScan({
  "cn.koala.authorization.repository",
  "cn.koala.authorization.client.repository",
  "cn.koala.security.authentication.event.repository"
})
public class AuthorizationServerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "oauth2AuthorizationServerPostProcessor")
  public AuthorizationServerSecurityFilterChainPostProcessor oauth2AuthorizationServerPostProcessor() {
    return new OAuth2AuthorizationServerPostProcessor();
  }

  @Bean(name = "oauth2ResourceOwnerPasswordPostProcessor")
  @ConditionalOnProperty(prefix = "koala.security.authorization-server.grant-type", name = "password", havingValue = "true")
  public AuthorizationServerSecurityFilterChainPostProcessor oauth2ResourceOwnerPasswordPostProcessor() {
    return new OAuth2ResourceOwnerPasswordPostProcessor();
  }

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ConditionalOnMissingBean(name = "authorizationServerSecurityFilterChain")
  public SecurityFilterChain authorizationServerSecurityFilterChain(
    HttpSecurity http, List<AuthorizationServerSecurityFilterChainPostProcessor> processors) throws Exception {
    for (AuthorizationServerSecurityFilterChainPostProcessor processor : processors) {
      processor.postProcessBeforeBuild(http);
    }
    SecurityFilterChain result = http.build();
    for (AuthorizationServerSecurityFilterChainPostProcessor processor : processors) {
      processor.postProcessAfterBuild(http);
    }
    return result;
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcRegisteredClientRepository(jdbcTemplate);
  }

  @Bean
  @ConditionalOnMissingBean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean(name = "adminRegisteredClientRegistrar")
  public RegisteredClientRegistrar adminRegisteredClientRegistrar(PasswordEncoder passwordEncoder,
                                                                  AuthorizationServerProperties properties) {
    return new AdminRegisteredClientRegistrar(passwordEncoder, properties.getGrantType().isPassword());
  }

  @Bean
  @ConditionalOnMissingBean(name = "registeredClientRegister")
  public RegisteredClientRegister registeredClientRegister(List<RegisteredClientRegistrar> registrars,
                                                           RegisteredClientRepository registeredClientRepository) {
    return new RegisteredClientRegister(registrars, registeredClientRepository);
  }

  @Bean
  public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
                                                         RegisteredClientRepository registeredClientRepository) {
    JdbcOAuth2AuthorizationService service = new JdbcOAuth2AuthorizationService(jdbcTemplate,
      registeredClientRepository);
    JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper authorizationRowMapper =
      new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(registeredClientRepository);
    authorizationRowMapper.setLobHandler(new DefaultLobHandler());
    ObjectMapper objectMapper = new ObjectMapper();
    ClassLoader classLoader = JdbcOAuth2AuthorizationService.class.getClassLoader();
    List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
    objectMapper.registerModules(securityModules);
    objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
    // 增加UserDetails实现混入
    objectMapper.addMixIn(KoalaUser.class, KoalaUserMixin.class);
    authorizationRowMapper.setObjectMapper(objectMapper);
    service.setAuthorizationRowMapper(authorizationRowMapper);
    return service;
  }

  @Bean
  public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
                                                                       RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
  }

  @Bean
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }

  @Bean
  public JWKSource<SecurityContext> jwkSource(AuthorizationServerProperties properties)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    JWKSet jwkSet = properties.getJwk().getJwkSet();
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  @Bean
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }

  @Bean
  @ConditionalOnMissingBean(name = "claimGrantTypeAccessTokenCustomizer")
  public JwtOAuth2TokenCustomizer claimGrantTypeAccessTokenCustomizer() {
    return new ClaimGrantTypeAccessTokenCustomizer();
  }

  @Bean
  @ConditionalOnMissingBean(name = "userAuthenticationAccessTokenCustomizer")
  public JwtOAuth2TokenCustomizer userAuthenticationAccessTokenCustomizer() {
    return new UserAuthenticationAccessTokenCustomizer();
  }

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(List<JwtOAuth2TokenCustomizer> customizers) {
    return new CompositeJwtOAuth2TokenCustomizer(customizers);
  }
}
