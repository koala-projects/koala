package cn.koala.security.autoconfigure;

import cn.koala.security.log.LoginLogService;
import cn.koala.security.log.UsernamePasswordAuthenticationLogListener;
import cn.koala.security.persist.SecurityAuditorAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * 安全自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class SecurityAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AuditorAware<?> auditorAware() {
    return new SecurityAuditorAware();
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(LoginLogService.class)
  public UsernamePasswordAuthenticationLogListener usernamePasswordAuthenticationLogListener(
    LoginLogService loginLogService) {

    return new UsernamePasswordAuthenticationLogListener(loginLogService);
  }
}
