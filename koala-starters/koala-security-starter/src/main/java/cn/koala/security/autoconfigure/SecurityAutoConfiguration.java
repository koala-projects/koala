package cn.koala.security.autoconfigure;

import cn.koala.mybatis.AuditorIdSupplier;
import cn.koala.security.CustomAuthoritiesOpaqueTokenIntrospector;
import cn.koala.security.JwtHelper;
import cn.koala.security.SecurityExceptionHandler;
import cn.koala.security.SecurityHelper;
import cn.koala.security.UserDetailsImpl;
import cn.koala.security.UserDetailsImplMixin;
import cn.koala.security.UserDetailsServiceImpl;
import cn.koala.security.UserinfoApi;
import cn.koala.security.UserinfoApiImpl;
import cn.koala.security.repositories.UserDetailsRepository;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Spring Authorization Server自动配置
 *
 * @author Houtaroy
 */
@EnableConfigurationProperties(JwtProperties.class)
@Configuration
@EnableMethodSecurity
@MapperScan("cn.koala.security.repositories")
public class SecurityAutoConfiguration {
  @Bean
  @Order(1)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
    throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
      .oidc(Customizer.withDefaults());  // Enable OpenID Connect 1.0
    http
      // Redirect to the login page when not authenticated from the
      // authorization endpoint
      .exceptionHandling((exceptions) -> exceptions
        .authenticationEntryPoint(
          new LoginUrlAuthenticationEntryPoint("/login"))
      )
      // Accept access tokens for User Info and/or Client Registration
      .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    return http.build();
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate,
                                                               PasswordEncoder passwordEncoder) {
    JdbcRegisteredClientRepository jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("koala-admin")
      .clientSecret(passwordEncoder.encode("123456"))
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .redirectUri("https://127.0.0.1:3100/login")
      .redirectUri("http://127.0.0.1:9000/swagger-ui/oauth2-redirect.html")
      .scope(OidcScopes.OPENID)
      .scope(OidcScopes.PROFILE)
      .scope("all")
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
      .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(30)).build())
      .build();
    if (jdbcRegisteredClientRepository.findByClientId(registeredClient.getClientId()) == null) {
      jdbcRegisteredClientRepository.save(registeredClient);
    }
    return jdbcRegisteredClientRepository;
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
  public OAuth2AuthorizationConsentService authorizationConsentService(
    JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
  }

  @Bean
  public UserDetailsService userDetailsService(UserDetailsRepository userDetailsRepository) {
    return new UserDetailsServiceImpl(userDetailsRepository);
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
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
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
  @Order(2)
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
    throws Exception {
    http.csrf().disable();
    http.authorizeHttpRequests().requestMatchers("/swagger*/**", "/v3/api-docs/**").permitAll();
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
    http.formLogin(Customizer.withDefaults());
    http.oauth2ResourceServer().opaqueToken();
    return http.build();
  }

  @Bean
  public OpaqueTokenIntrospector introspector() {
    return new CustomAuthoritiesOpaqueTokenIntrospector();
  }

  @Bean
  @ConditionalOnMissingBean
  public AuditorIdSupplier<?> auditorIdSupplier() {
    return SecurityHelper::getCurrentUserId;
  }

  @Bean
  public SecurityExceptionHandler securityExceptionHandler() {
    return new SecurityExceptionHandler();
  }

  @Bean
  @ConditionalOnMissingBean
  public UserinfoApi userinfoApi() {
    return new UserinfoApiImpl();
  }
}
