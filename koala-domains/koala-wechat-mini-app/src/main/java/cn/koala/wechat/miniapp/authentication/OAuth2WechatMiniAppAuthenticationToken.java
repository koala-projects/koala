package cn.koala.wechat.miniapp.authentication;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;


@Getter
public class OAuth2WechatMiniAppAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
  private final String code;

  public OAuth2WechatMiniAppAuthenticationToken(String code, Authentication clientPrincipal,
                                                @Nullable Map<String, Object> additionalParameters) {
    super(WechatGrantType.MINI_APP, clientPrincipal, additionalParameters);
    this.code = code;
  }
}
