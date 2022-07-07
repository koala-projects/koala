package cn.koala.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

import java.util.List;

/**
 * 委派Jwt上下文定制器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DelegatingJwtEncodingContextCustomizer implements JwtEncodingContextCustomizer {
  protected final List<JwtEncodingContextCustomizer> customizers;

  @Override
  public void customize(JwtEncodingContext context) {
    customizers.forEach(customizer -> customizer.customize(context));
  }
}
