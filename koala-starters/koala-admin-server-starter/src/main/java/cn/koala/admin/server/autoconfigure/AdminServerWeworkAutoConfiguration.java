package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.strategy.support.WeworkNotifyStrategy;
import com.binarywang.spring.starter.wxjava.cp.config.WxCpAutoConfiguration;
import me.chanjar.weixin.cp.api.WxCpService;
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
@AutoConfigureBefore(AdminServerAutoConfiguration.class)
@AutoConfigureAfter(WxCpAutoConfiguration.class)
public class AdminServerWeworkAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "weworkStrategy")
  @ConditionalOnBean(WxCpService.class)
  public WeworkNotifyStrategy weworkStrategy(WxCpService wxCpService) {
    return new WeworkNotifyStrategy(wxCpService);
  }
}
