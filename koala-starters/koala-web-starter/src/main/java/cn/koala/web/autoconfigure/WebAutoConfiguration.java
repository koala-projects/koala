package cn.koala.web.autoconfigure;

import cn.koala.web.RestExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class WebAutoConfiguration {
  /**
   * Rest风格异常处理器的bean
   *
   * @return Rest风格异常处理器
   */
  @Bean
  @ConditionalOnMissingBean
  public RestExceptionHandler restExceptionHandler() {
    return new RestExceptionHandler();
  }
}
