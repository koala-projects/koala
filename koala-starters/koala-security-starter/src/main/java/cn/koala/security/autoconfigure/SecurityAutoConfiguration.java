package cn.koala.security.autoconfigure;

import cn.koala.security.authentication.event.AuthenticateLogListener;
import cn.koala.security.authentication.event.AuthenticateLogService;
import cn.koala.security.persist.SecurityAuditorAware;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  @ConditionalOnBean(AuthenticateLogService.class)
  public AuthenticateLogListener authenticateLogListener(ObjectMapper objectMapper,
                                                         AuthenticateLogService authenticateLogService) {

    return new AuthenticateLogListener(objectMapper, authenticateLogService);
  }
}
