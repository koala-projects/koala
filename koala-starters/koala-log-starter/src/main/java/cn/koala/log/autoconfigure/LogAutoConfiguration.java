package cn.koala.log.autoconfigure;

import cn.koala.log.LogAspect;
import cn.koala.log.LogProperties;
import cn.koala.log.api.LogApi;
import cn.koala.log.api.LogApiImpl;
import cn.koala.log.repository.LogRepository;
import cn.koala.log.service.LogService;
import cn.koala.log.service.LogServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * 日志自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
@MapperScan(basePackages = "cn.koala.log.repository")
public class LogAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public LogService logService(LogRepository logRepository) {
    return new LogServiceImpl(logRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public LogApi logApi(LogService logService) {
    return new LogApiImpl(logService);
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "koala.log", name = "enabled", matchIfMissing = true)
  public LogAspect logAspect(LogProperties properties, LogService logService,
                             ObjectProvider<AuditorAware<?>> auditorAware, ObjectMapper objectMapper) {
    return new LogAspect(properties.getIgnoredPatterns(), auditorAware, logService, objectMapper);
  }
}
