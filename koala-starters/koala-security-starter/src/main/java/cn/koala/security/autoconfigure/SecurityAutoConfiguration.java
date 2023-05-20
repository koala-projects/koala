package cn.koala.security.autoconfigure;

import cn.koala.security.JwtProperties;
import cn.koala.security.SecurityInitializer;
import cn.koala.security.SecurityProperties;
import cn.koala.security.SpringSecurityExceptionHandler;
import cn.koala.security.apis.LoginController;
import cn.koala.security.apis.UserinfoApi;
import cn.koala.security.apis.UserinfoApiImpl;
import cn.koala.security.persist.SpringSecurityAuditorAware;
import cn.koala.security.repositories.UserDetailsRepository;
import cn.koala.security.services.UserinfoService;
import cn.koala.security.services.UserinfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全自动配置类
 *
 * @author Houtaroy
 */
@EnableConfigurationProperties({JwtProperties.class, SecurityProperties.class})
@Import({AuthorizationServerAutoConfiguration.class, DefaultSecurityAutoConfiguration.class})
@MapperScan("cn.koala.security.repositories")
@Configuration
@RequiredArgsConstructor
public class SecurityAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
  public AuditorAware<?> auditorAware() {
    return new SpringSecurityAuditorAware();
  }

  @Bean
  public SpringSecurityExceptionHandler securityExceptionHandler() {
    return new SpringSecurityExceptionHandler();
  }

  @Bean
  public SecurityInitializer securityInitializer() {
    return new SecurityInitializer();
  }
}
