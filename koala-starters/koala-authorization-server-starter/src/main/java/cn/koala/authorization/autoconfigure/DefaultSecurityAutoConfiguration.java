package cn.koala.authorization.autoconfigure;

import cn.koala.authorization.LoginController;
import cn.koala.authorization.UserinfoApi;
import cn.koala.authorization.UserinfoService;
import cn.koala.authorization.builder.support.FormLoginPostProcessor;
import cn.koala.authorization.builder.support.LogoutSuccessHandlerPostProcessor;
import cn.koala.authorization.client.RegisteredClientApi;
import cn.koala.authorization.client.RegisteredClientService;
import cn.koala.authorization.client.repository.RegisteredClientMyBatisRepository;
import cn.koala.authorization.client.support.DefaultRegisteredClientApi;
import cn.koala.authorization.client.support.DefaultRegisteredClientService;
import cn.koala.authorization.repository.KoalaUserRepository;
import cn.koala.authorization.support.DefaultUserDetailsService;
import cn.koala.authorization.support.DefaultUserinfoApi;
import cn.koala.authorization.support.DefaultUserinfoService;
import cn.koala.resource.builder.ResourceServerSecurityFilterChainPostProcessor;
import cn.koala.resource.builder.support.PermitAllPostProcessor;
import cn.koala.security.log.AuthenticateLogService;
import cn.koala.security.log.DefaultAuthenticateLogService;
import cn.koala.security.log.DefaultLoginLogApi;
import cn.koala.security.log.DefaultLoginLogService;
import cn.koala.security.log.LoginLogApi;
import cn.koala.security.log.LoginLogService;
import cn.koala.security.log.OAuth2AuthorizationCodeRequestAuthenticationLogListener;
import cn.koala.security.log.repository.AuthenticateLogRepository;
import cn.koala.security.log.repository.LoginLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * 认证授权服务默认安全配置类
 *
 * @author Houtaroy
 */
@Configuration
public class DefaultSecurityAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "formLoginPostProcessor")
  public ResourceServerSecurityFilterChainPostProcessor formLoginPostProcessor(AuthorizationServerProperties properties) {
    return new FormLoginPostProcessor(properties.isCustomLoginPage());
  }

  @Bean
  @ConditionalOnProperty(prefix = "koala.security.authorization-server", name = "custom-login-page", havingValue = "true")
  public LoginController loginController() {
    return new LoginController();
  }

  @Bean
  @Order(3110)
  @ConditionalOnMissingBean(name = "loginPermitAllPostProcessor")
  public ResourceServerSecurityFilterChainPostProcessor LoginPermitAllPostProcessor() {
    return new PermitAllPostProcessor("/login/**");
  }

  @Bean
  @ConditionalOnMissingBean(name = "logoutSuccessHandlerPostProcessor")
  public ResourceServerSecurityFilterChainPostProcessor logoutSuccessHandlerPostProcessor() {
    return new LogoutSuccessHandlerPostProcessor();
  }

  @Bean
  @Order(3100)
  @ConditionalOnMissingBean(name = "defaultSecurityFilterChain")
  public SecurityFilterChain defaultSecurityFilterChain(
    HttpSecurity http, List<ResourceServerSecurityFilterChainPostProcessor> processors) throws Exception {
    for (ResourceServerSecurityFilterChainPostProcessor processor : processors) {
      processor.postProcessBeforeBuild(http);
    }
    SecurityFilterChain result = http.build();
    for (ResourceServerSecurityFilterChainPostProcessor processor : processors) {
      processor.postProcessAfterBuild(http);
    }
    return result;
  }

  @Bean
  public UserDetailsService userDetailsService(KoalaUserRepository repository) {
    return new DefaultUserDetailsService(repository);
  }

  @Bean
  @ConditionalOnMissingBean
  public RegisteredClientService registeredClientService(RegisteredClientRepository repository,
                                                         RegisteredClientMyBatisRepository myBatisRepository) {
    return new DefaultRegisteredClientService(repository, myBatisRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public RegisteredClientApi registeredClientApi(RegisteredClientService service) {
    return new DefaultRegisteredClientApi(service);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserinfoService userinfoService(PasswordEncoder passwordEncoder, KoalaUserRepository koalaUserRepository) {
    return new DefaultUserinfoService(passwordEncoder, koalaUserRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserinfoApi userinfoApi(UserinfoService userinfoService) {
    return new DefaultUserinfoApi(userinfoService);
  }

  @Bean
  @ConditionalOnMissingBean
  public AuthenticateLogService authenticateLogService(AuthenticateLogRepository authenticateLogRepository) {
    return new DefaultAuthenticateLogService(authenticateLogRepository);
  }

  @Bean
  @ConditionalOnMissingBean(name = "oauth2AuthorizationCodeRequestAuthenticationLogListener")
  public OAuth2AuthorizationCodeRequestAuthenticationLogListener
  oauth2AuthorizationCodeRequestAuthenticationLogListener(ObjectMapper objectMapper,
                                                          AuthenticateLogService authenticateLogService) {

    return new OAuth2AuthorizationCodeRequestAuthenticationLogListener(objectMapper, authenticateLogService);
  }

  @Bean
  @ConditionalOnMissingBean
  public LoginLogService loginLogService(LoginLogRepository loginLogRepository) {
    return new DefaultLoginLogService(loginLogRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public LoginLogApi loginLogApi(LoginLogService loginLogService) {
    return new DefaultLoginLogApi(loginLogService);
  }
}
