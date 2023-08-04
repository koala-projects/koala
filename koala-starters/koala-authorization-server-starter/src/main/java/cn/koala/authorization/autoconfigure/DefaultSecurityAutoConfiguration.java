package cn.koala.authorization.autoconfigure;

import cn.koala.authorization.LoginController;
import cn.koala.authorization.builder.support.FormLoginPostProcessor;
import cn.koala.authorization.builder.support.LogoutSuccessHandlerPostProcessor;
import cn.koala.authorization.repository.KoalaUserRepository;
import cn.koala.authorization.support.DefaultUserDetailsService;
import cn.koala.resource.builder.ResourceServerSecurityFilterChainPostProcessor;
import cn.koala.resource.builder.support.PermitAllPostProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * 认证授权服务默认安全配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableWebSecurity
@MapperScan("cn.koala.authorization.repository")
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
}
