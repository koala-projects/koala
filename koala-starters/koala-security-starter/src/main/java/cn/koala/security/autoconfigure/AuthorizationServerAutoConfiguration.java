package cn.koala.security.autoconfigure;

import cn.koala.security.AuthoritiesOpaqueTokenIntrospector;
import cn.koala.security.JwtHelper;
import cn.koala.security.JwtProperties;
import cn.koala.security.SecurityProperties;
import cn.koala.security.builder.AuthorizationServerBuilder;
import cn.koala.security.builder.processor.AuthorizationServerProcessor;
import cn.koala.security.builder.processor.support.OAuth2AuthorizationServerProcessor;
import cn.koala.security.builder.processor.support.OAuth2ResourceOwnerPasswordProcessor;
import cn.koala.security.builder.support.DefaultAuthorizationServerBuilder;
import cn.koala.security.client.CompositeRegisteredClientRegistrar;
import cn.koala.security.client.DefaultRegisteredClientRegistrar;
import cn.koala.security.client.RegisteredClientRegistrar;
import cn.koala.security.entities.UserDetailsImpl;
import cn.koala.security.entities.UserDetailsImplMixin;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.List;

/**
 * 授权服务自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class AuthorizationServerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "authorizationServerProcessor")
  public AuthorizationServerProcessor authorizationServerProcessor() {
    return new OAuth2AuthorizationServerProcessor();
  }

  @Bean(name = "resourceOwnerPasswordProcessor")
  @ConditionalOnProperty(prefix = "koala.security.grant-type", name = "password", havingValue = "true")
  public AuthorizationServerProcessor resourceOwnerPasswordPostProcessor() {
    return new OAuth2ResourceOwnerPasswordProcessor();
  }

  @Bean
  @ConditionalOnMissingBean
  public AuthorizationServerBuilder authorizationServerBuilder(List<AuthorizationServerProcessor> processors) {
    return new DefaultAuthorizationServerBuilder(processors);
  }

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ConditionalOnMissingBean(name = "authorizationServerSecurityFilterChain")
  public SecurityFilterChain authorizationServerSecurityFilterChain(
    HttpSecurity http, AuthorizationServerBuilder builder) throws Exception {
    return builder.build(http);
  }

  @Bean
  public OpaqueTokenIntrospector introspector(OAuth2ResourceServerProperties properties) {
    OAuth2ResourceServerProperties.Opaquetoken opaquetoken = properties.getOpaquetoken();
    return new AuthoritiesOpaqueTokenIntrospector(
      opaquetoken.getIntrospectionUri(),
      opaquetoken.getClientId(),
      opaquetoken.getClientSecret()
    );
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcRegisteredClientRepository(jdbcTemplate);
  }

  @Bean
  @ConditionalOnMissingBean(name = "defaultRegisteredClientRegistrar")
  public RegisteredClientRegistrar defaultRegisteredClientRegistrar(SecurityProperties properties,
                                                                    PasswordEncoder passwordEncoder) {
    return new DefaultRegisteredClientRegistrar(properties, passwordEncoder);
  }

  @Bean
  public CompositeRegisteredClientRegistrar compositeRegisteredClientRegistrar(List<RegisteredClientRegistrar> registries,
                                                                               RegisteredClientRepository repository) {
    return new CompositeRegisteredClientRegistrar(registries, repository);
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
    objectMapper.addMixIn(UserDetailsImpl.class, UserDetailsImplMixin.class);
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
  public JWKSource<SecurityContext> jwkSource(JwtProperties properties)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    JWKSet jwkSet = new JWKSet(
      JwtHelper.generateRsa(properties.getKeyID(), properties.getPublicKey(), properties.getPrivateKey())
    );
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  @Bean
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
    return context -> {
      JwtClaimsSet.Builder claims = context.getClaims();
      if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
        if (context.getPrincipal().getPrincipal() instanceof UserDetailsImpl userDetails) {
          claims.claim("id", userDetails.getId().toString());
          claims.claim("username", userDetails.getUsername());
          claims.claim("nickname", userDetails.getNickname());
          claims.claim("scope", new HashSet<>(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()));
        }
      }
    };
  }

  @Bean
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }
}
