package cn.koala.security.autoconfigure;

import cn.koala.security.persist.SecurityAuditorAware;
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
}
