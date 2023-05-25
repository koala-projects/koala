package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.strategy.FallbackStrategy;
import cn.koala.admin.server.strategy.NotifyStrategy;
import cn.koala.admin.server.strategy.support.DefaultFallbackStrategy;
import cn.koala.admin.server.strategy.support.WeworkStrategy;
import com.binarywang.spring.starter.wxjava.cp.config.WxCpAutoConfiguration;
import me.chanjar.weixin.cp.api.WxCpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Admin Server 企业微信自动配置类
 * <p>
 * 基于<a href="https://github.com/Wechat-Group/WxJava">WxJava</a>
 * <p>
 * 当存在依赖wx-java-cp-spring-boot-starter时自动装配
 *
 * @author Houtaroy
 */
@ConditionalOnClass(WxCpAutoConfiguration.class)
@AutoConfigureBefore(AdminServerNotifyAutoConfiguration.class)
@AutoConfigureAfter(WxCpAutoConfiguration.class)
public class AdminServerWeworkAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "weworkStrategy")
  @ConditionalOnBean(WxCpService.class)
  public NotifyStrategy weworkStrategy(WxCpService wxCpService) {
    return new WeworkStrategy(wxCpService);
  }

  @Bean
  @ConditionalOnMissingBean(name = "")
  @ConditionalOnBean(name = "weworkStrategy")
  public FallbackStrategy fallbackStrategy(AdminServerProperties properties,
                                           @Qualifier("weworkStrategy") NotifyStrategy weworkStrategy) {
    return new DefaultFallbackStrategy(properties.getFallbackMaintainer(), weworkStrategy);
  }
}
