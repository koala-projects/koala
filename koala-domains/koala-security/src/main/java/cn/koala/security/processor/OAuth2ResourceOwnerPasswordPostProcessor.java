package cn.koala.security.processor;

import cn.koala.security.authentication.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import cn.koala.security.authentication.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * OAuth2密码模式处理器
 *
 * @author Houtaroy
 */
public class OAuth2ResourceOwnerPasswordPostProcessor implements AuthorizationServerPostProcessor {

  @Override
  public void postProcessBeforeInitialization(HttpSecurity http) {
    OAuth2AuthorizationServerConfigurer configurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
    configurer.tokenEndpoint(
      endpoint -> endpoint.accessTokenRequestConverter(new OAuth2ResourceOwnerPasswordAuthenticationConverter())
    );
  }

  @Override
  public void postProcessAfterInitialization(HttpSecurity http) {
    AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
    OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
    OAuth2TokenGenerator<?> tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);
    http.authenticationProvider(
      new OAuth2ResourceOwnerPasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator)
    );
  }
}
