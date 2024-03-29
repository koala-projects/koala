package cn.koala.wechat.miniapp.processor;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.koala.authorization.builder.AuthorizationServerSecurityFilterChainPostProcessor;
import cn.koala.wechat.miniapp.WechatMiniAppUserRegistrar;
import cn.koala.wechat.miniapp.authentication.OAuth2WechatMiniAppAuthenticationConverter;
import cn.koala.wechat.miniapp.authentication.OAuth2WechatMiniAppAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * OAuth2微信小程序模式处理器
 *
 * @author Houtaroy
 */
@Order(3300)
@RequiredArgsConstructor
public class OAuth2WechatMiniAppPostProcessor implements AuthorizationServerSecurityFilterChainPostProcessor {

  private final WxMaService wxMaService;

  private final WechatMiniAppUserRegistrar wechatMiniAppUserRegistry;

  @Override
  public void postProcessBeforeBuild(HttpSecurity http) {
    OAuth2AuthorizationServerConfigurer configurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
    configurer.tokenEndpoint(
      endpoint -> endpoint.accessTokenRequestConverter(new OAuth2WechatMiniAppAuthenticationConverter())
    );
  }

  @Override
  @SuppressWarnings("unchecked")
  public void postProcessAfterBuild(HttpSecurity http) {
    AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
    OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);
    http.authenticationProvider(new OAuth2WechatMiniAppAuthenticationProvider(
      wxMaService, wechatMiniAppUserRegistry, authenticationManager, authorizationService, tokenGenerator
    ));
  }
}
