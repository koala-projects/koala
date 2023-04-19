package cn.koala.wechat.miniapp.autoconfigure;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.koala.security.client.RegisteredClientRegistry;
import cn.koala.security.processor.AuthorizationServerPostProcessor;
import cn.koala.system.repositories.UserRepository;
import cn.koala.wechat.miniapp.processor.OAuth2WechatMiniAppPostProcessor;
import cn.koala.wechat.miniapp.registry.SimpleWechatMiniAppUserRegistry;
import cn.koala.wechat.miniapp.registry.WechatMiniAppClientRegistry;
import cn.koala.wechat.miniapp.registry.WechatMiniAppUserRegistry;
import cn.koala.wechat.miniapp.repository.WechatMiniAppUserRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 微信小程序自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = "cn.koala.wechat.miniapp.repository")
public class WechatMiniAppAutoConfiguration {
  @Bean
  public AuthorizationServerPostProcessor wechatMiniAppPostProcessor(WxMaService wxMaService,
                                                                     WechatMiniAppUserRegistry wechatMiniAppUserRegistry) {
    return new OAuth2WechatMiniAppPostProcessor(wxMaService, wechatMiniAppUserRegistry);
  }

  @Bean
  public WechatMiniAppUserRegistry wechatMiniAppUserRegistry(PasswordEncoder passwordEncoder,
                                                             UserRepository userRepository,
                                                             WechatMiniAppUserRepository wechatMiniAppUserRepository) {
    return new SimpleWechatMiniAppUserRegistry(passwordEncoder, userRepository, wechatMiniAppUserRepository);
  }

  @Bean
  @ConditionalOnMissingBean(name = "wechatMiniAppClientRegistry")
  public RegisteredClientRegistry wechatMiniAppClientRegistry(PasswordEncoder passwordEncoder) {
    return new WechatMiniAppClientRegistry(passwordEncoder);
  }
}
