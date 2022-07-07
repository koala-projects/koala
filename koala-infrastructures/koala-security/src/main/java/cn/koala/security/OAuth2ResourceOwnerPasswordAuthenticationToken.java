package cn.koala.security;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;

import java.util.Map;

/**
 * 密码模式认证Token
 *
 * @author Houtaroy
 */
@Getter
@SuppressWarnings("PMD")
public class OAuth2ResourceOwnerPasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
  protected UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

  /**
   * 构造函数
   *
   * @param authorizationGrantType              授权类型
   * @param clientPrincipal                     客户端信息
   * @param usernamePasswordAuthenticationToken 用户密码认证Token
   * @param additionalParameters                附加参数
   */
  public OAuth2ResourceOwnerPasswordAuthenticationToken(@NonNull AuthorizationGrantType authorizationGrantType,
                                                        @NonNull OAuth2ClientAuthenticationToken clientPrincipal,
                                                        @NonNull UsernamePasswordAuthenticationToken
                                                          usernamePasswordAuthenticationToken,
                                                        @Nullable Map<String, Object> additionalParameters) {
    super(authorizationGrantType, clientPrincipal, additionalParameters);
    this.usernamePasswordAuthenticationToken = usernamePasswordAuthenticationToken;
  }
}
