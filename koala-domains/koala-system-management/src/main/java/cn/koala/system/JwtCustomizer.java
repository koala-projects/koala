package cn.koala.system;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Optional;

/**
 * Jwt定制器
 * 在令牌中追加了用户id
 *
 * @author Houtaroy
 */
public class JwtCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

  @Override
  public void customize(JwtEncodingContext context) {
    Optional.ofNullable(context).map(OAuth2TokenContext::getPrincipal)
      .filter(principal -> UsernamePasswordAuthenticationToken.class.isAssignableFrom(principal.getClass()))
      .map(UsernamePasswordAuthenticationToken.class::cast)
      .map(UsernamePasswordAuthenticationToken::getPrincipal)
      .filter(principal -> User.class.isAssignableFrom(principal.getClass()))
      .map(User.class::cast)
      .ifPresent(user -> context.getClaims().claim("user_id", user.getId()));
  }
}
