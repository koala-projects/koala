package cn.koala.wechat.miniapp.support;

import cn.koala.authorization.client.RegisteredClientRegistrar;
import cn.koala.wechat.miniapp.authentication.WechatGrantType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

/**
 * 微信小程序客户端注册
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class WechatMiniAppClientRegistrar implements RegisteredClientRegistrar {

  private final PasswordEncoder passwordEncoder;

  @Override
  public RegisteredClient getRegisteredClient() {
    return RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("koala-wechat-mini-app")
      .clientSecret(passwordEncoder.encode("123456"))
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .authorizationGrantType(WechatGrantType.MINI_APP)
      .redirectUri("https://127.0.0.1:3100/login")
      .redirectUri("http://127.0.0.1:9000/swagger-ui/oauth2-redirect.html")
      .scope(OidcScopes.OPENID)
      .scope(OidcScopes.PROFILE)
      .scope("all")
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
      .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(30)).build())
      .build();
  }
}
