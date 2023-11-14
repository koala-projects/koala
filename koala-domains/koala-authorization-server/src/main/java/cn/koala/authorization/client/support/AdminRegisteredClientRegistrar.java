package cn.koala.authorization.client.support;

import cn.koala.authorization.client.RegisteredClientRegistrar;
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
 * 管理注册客户端登记器
 * <p>
 * 自定注册koala-admin客户端
 *
 * @author Houtaroy
 */
public class AdminRegisteredClientRegistrar implements RegisteredClientRegistrar {

  private final PasswordEncoder passwordEncoder;

  private final boolean supportGrantTypePassword;

  public AdminRegisteredClientRegistrar(PasswordEncoder passwordEncoder) {
    this(passwordEncoder, false);
  }

  public AdminRegisteredClientRegistrar(PasswordEncoder passwordEncoder, boolean supportGrantTypePassword) {
    this.passwordEncoder = passwordEncoder;
    this.supportGrantTypePassword = supportGrantTypePassword;
  }

  @Override
  public RegisteredClient getRegisteredClient() {
    RegisteredClient.Builder builder = RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("koala-admin")
      .clientSecret(passwordEncoder.encode("123456"))
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .redirectUri("http://127.0.0.1:3100/authorize")
      .redirectUri("http://127.0.0.1:9000/swagger-ui/oauth2-redirect.html")
      .scope(OidcScopes.OPENID)
      .scope(OidcScopes.PROFILE)
      .scope("all")
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
      .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(30)).build());
    if (supportGrantTypePassword) {
      builder.authorizationGrantType(AuthorizationGrantType.PASSWORD);
    }
    return builder.build();
  }
}
