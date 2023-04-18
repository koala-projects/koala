package cn.koala.security.authentication;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;


@Getter
public class OAuth2ResourceOwnerPasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
  private final String username;
  private final String password;

  public OAuth2ResourceOwnerPasswordAuthenticationToken(String username, String password,
                                                        Authentication clientPrincipal,
                                                        @Nullable Map<String, Object> additionalParameters) {
    super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
    this.username = username;
    this.password = password;
  }
}
