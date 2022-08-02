package cn.koala.oauth2;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.web.authentication.DelegatingAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2ClientCredentialsAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2RefreshTokenAuthenticationConverter;

import java.util.Arrays;

/**
 * OAuth2端点配置定制器
 *
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class OAuth2TokenEndpointConfigurerCustomizer implements Customizer<OAuth2TokenEndpointConfigurer> {
  @Override
  public void customize(OAuth2TokenEndpointConfigurer configurer) {
    configurer.accessTokenRequestConverter(
      new DelegatingAuthenticationConverter(Arrays.asList(
        new OAuth2AuthorizationCodeAuthenticationConverter(),
        new OAuth2PasswordAuthenticationConverter(),
        new OAuth2ClientCredentialsAuthenticationConverter(),
        new OAuth2RefreshTokenAuthenticationConverter()
      ))
    );
  }
}
