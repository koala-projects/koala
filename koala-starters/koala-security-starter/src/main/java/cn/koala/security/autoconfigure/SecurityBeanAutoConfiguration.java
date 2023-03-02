package cn.koala.security.autoconfigure;

import cn.koala.mybatis.AuditorIdSupplier;
import cn.koala.security.LoginController;
import cn.koala.security.SecurityExceptionHandler;
import cn.koala.security.SecurityHelper;
import cn.koala.security.UserDetailsImpl;
import cn.koala.security.UserDetailsImplMixin;
import cn.koala.security.UserDetailsServiceImpl;
import cn.koala.security.UserinfoApi;
import cn.koala.security.UserinfoApiImpl;
import cn.koala.security.UserinfoService;
import cn.koala.security.UserinfoServiceImpl;
import cn.koala.security.repositories.UserDetailsRepository;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * 安全Bean自动配置类
 *
 * @author Houtaroy
 */
@MapperScan("cn.koala.security.repositories")
public class SecurityBeanAutoConfiguration {
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
  @ConditionalOnMissingBean
  public LoginController loginController() {
    return new LoginController();
  }

  @Bean
  @ConditionalOnMissingBean
  public UserinfoService userinfoService(PasswordEncoder passwordEncoder, UserDetailsRepository userDetailsRepository) {
    return new UserinfoServiceImpl(passwordEncoder, userDetailsRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserinfoApi userinfoApi(UserinfoService userinfoService) {
    return new UserinfoApiImpl(userinfoService);
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
}
