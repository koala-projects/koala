package cn.koala.security.authentication.client.support;

import cn.koala.security.authentication.client.AbstractRegisteredClientRegistrar;
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
 * 默认注册客户端注册器
 * <p>
 * 自定注册koala-admin客户端
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultRegisteredClientRegistrar extends AbstractRegisteredClientRegistrar {

  private final PasswordEncoder passwordEncoder;
  private final boolean password;

  @Override
  protected RegisteredClient obtainRegisteredClient() {
    RegisteredClient.Builder builder = RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("koala-admin")
      .clientSecret(passwordEncoder.encode("123456"))
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .redirectUri("https://127.0.0.1:3100/authorize")
      .redirectUri("http://127.0.0.1:4200/swagger-ui/oauth2-redirect.html")
      .scope(OidcScopes.OPENID)
      .scope(OidcScopes.PROFILE)
      .scope("all")
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
      .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(30)).build());
    if (password) {
      builder.authorizationGrantType(AuthorizationGrantType.PASSWORD);
    }
    return builder.build();
  }
}
