package cn.koala.authorization.token;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.List;

/**
 * 组合OAuth2Token自定义器
 *
 * @author Houtaroy
 */
public class CompositeJwtOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

  private final List<JwtOAuth2TokenCustomizer> customizers;

  public CompositeJwtOAuth2TokenCustomizer(List<JwtOAuth2TokenCustomizer> customizers) {
    this.customizers = customizers;
  }

  @Override
  public void customize(JwtEncodingContext context) {
    customizers.forEach(customizer -> customizer.customize(context));
  }
}
