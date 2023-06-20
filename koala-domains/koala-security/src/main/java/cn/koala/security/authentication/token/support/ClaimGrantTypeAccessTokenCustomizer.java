package cn.koala.security.authentication.token.support;

import cn.koala.security.authentication.token.JwtOAuth2TokenCustomizer;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

/**
 * 增加授权类型属性Access Token 定制器
 *
 * @author Houtaroy
 */
public class ClaimGrantTypeAccessTokenCustomizer implements JwtOAuth2TokenCustomizer {

  @Override
  public void customize(JwtEncodingContext context) {
    context.getClaims().claim(OAuth2ParameterNames.GRANT_TYPE, context.getAuthorizationGrantType().getValue());
  }
}
