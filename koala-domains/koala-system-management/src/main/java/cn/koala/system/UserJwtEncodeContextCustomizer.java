package cn.koala.system;

import cn.koala.security.JwtEncodingContextCustomizer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

/**
 * 用户Jwt上下文定制器
 *
 * @author Houtaroy
 */
public class UserJwtEncodeContextCustomizer implements JwtEncodingContextCustomizer {
  @Override
  public void customize(JwtEncodingContext context) {
    if (context.getPrincipal().getPrincipal() instanceof User user) {
      context.getClaims().claim("user_id", user.getId());
    }
  }
}
