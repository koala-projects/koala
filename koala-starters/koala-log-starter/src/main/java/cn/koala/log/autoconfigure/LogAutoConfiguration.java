package cn.koala.log.autoconfigure;

import cn.koala.log.LogAspect;
import cn.koala.log.apis.LogApi;
import cn.koala.log.apis.LogApiImpl;
import cn.koala.log.repositories.LogRepository;
import cn.koala.log.services.LogService;
import cn.koala.log.services.LogServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = "cn.koala.log.repositories")
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
  public LogAspect logAspect(LogService logService, ObjectMapper objectMapper) {
    return new LogAspect(logService, objectMapper);
  }
}
