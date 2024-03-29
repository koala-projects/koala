package cn.koala.wechat.miniapp.autoconfigure;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.koala.authorization.builder.AuthorizationServerSecurityFilterChainPostProcessor;
import cn.koala.authorization.client.RegisteredClientRegistrar;
import cn.koala.system.repository.UserRepository;
import cn.koala.wechat.miniapp.WechatMiniAppUserRegistrar;
import cn.koala.wechat.miniapp.processor.OAuth2WechatMiniAppPostProcessor;
import cn.koala.wechat.miniapp.repository.WechatMiniAppUserRepository;
import cn.koala.wechat.miniapp.support.DefaultWechatMiniAppUserRegistrar;
import cn.koala.wechat.miniapp.support.WechatMiniAppClientRegistrar;
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
  @ConditionalOnMissingBean(name = "oauth2WechatMiniAppPostProcessor")
  public AuthorizationServerSecurityFilterChainPostProcessor oauth2WechatMiniAppPostProcessor(
    WxMaService wxMaService, WechatMiniAppUserRegistrar wechatMiniAppUserRegistry) {
    return new OAuth2WechatMiniAppPostProcessor(wxMaService, wechatMiniAppUserRegistry);
  }

  @Bean
  @ConditionalOnMissingBean
  public WechatMiniAppUserRegistrar wechatMiniAppUserRegistrar(PasswordEncoder passwordEncoder,
                                                               UserRepository userRepository,
                                                               WechatMiniAppUserRepository wechatMiniAppUserRepository) {
    return new DefaultWechatMiniAppUserRegistrar(passwordEncoder, userRepository, wechatMiniAppUserRepository);
  }

  @Bean
  @ConditionalOnMissingBean(name = "wechatMiniAppClientRegistry")
  public RegisteredClientRegistrar wechatMiniAppClientRegistry(PasswordEncoder passwordEncoder) {
    return new WechatMiniAppClientRegistrar(passwordEncoder);
  }
}
