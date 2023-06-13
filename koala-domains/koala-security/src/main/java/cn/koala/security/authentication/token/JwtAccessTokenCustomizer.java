package cn.koala.security.authentication.token;

import cn.koala.security.userdetails.support.KoalaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * Jwt AccessToken 自定义器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class JwtAccessTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

  @Override
  public void customize(JwtEncodingContext context) {
    JwtClaimsSet.Builder claims = context.getClaims();
    if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
      if (context.getPrincipal().getPrincipal() instanceof KoalaUser userDetails) {
        claims.claim("id", userDetails.getId().toString());
        claims.claim("username", userDetails.getUsername());
        claims.claim("nickname", userDetails.getNickname());
      }
    }
  }
}
