package cn.koala.oauth2;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * OAuth2密码模式授权Token
 *
 * @author shihongjun
 */
@Getter
@SuppressWarnings("PMD")
public class OAuth2PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

  protected String username;
  protected String password;

  /**
   * OAuth2密码模式授权令牌
   *
   * @param clientPrincipal      客户端主题
   * @param username             用户名
   * @param password             密码
   * @param additionalParameters 附加参数
   */
  public OAuth2PasswordAuthenticationToken(Authentication clientPrincipal, String username, String password,
                                           @Nullable Map<String, Object> additionalParameters) {
    super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
    this.username = username;
    this.password = password;
  }
}
