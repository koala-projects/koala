package cn.houtaroy.koala.starter.dingtalk;

import cn.houtaroy.koala.component.dingtalk.DingtalkProperties;
import cn.houtaroy.koala.component.dingtalk.DingtalkService;
import cn.houtaroy.koala.component.dingtalk.InMemoryDingtalkService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(DingtalkProperties.class)
public class DingtalkAutoConfig {

  /**
   * 钉钉服务的bean
   *
   * @return 钉钉服务
   */
  @Bean
  @ConditionalOnMissingBean
  public DingtalkService dingtalkService(DingtalkProperties properties) {
    return new InMemoryDingtalkService(properties);
  }
}
