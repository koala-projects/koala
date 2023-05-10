package cn.koala.powerjob.worker.autoconfigure;

import cn.koala.powerjob.worker.GroovyProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PowerJob Worker自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class PowerJobWorkerAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public GroovyProcessor groovyProcessor() {
    return new GroovyProcessor();
  }
}
