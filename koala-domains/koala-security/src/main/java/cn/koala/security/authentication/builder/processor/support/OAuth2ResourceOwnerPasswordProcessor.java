package cn.koala.security.authentication.builder.processor.support;

import cn.koala.security.authentication.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import cn.koala.security.authentication.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import cn.koala.security.authentication.builder.processor.AuthorizationServerProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * OAuth2密码授权模式处理器
 *
 * @author Houtaroy
 */
@Order(2000)
public class OAuth2ResourceOwnerPasswordProcessor implements AuthorizationServerProcessor {

  @Override
  public void preBuild(HttpSecurity http) {
    OAuth2AuthorizationServerConfigurer configurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
    configurer.tokenEndpoint(
      endpoint -> endpoint.accessTokenRequestConverter(new OAuth2ResourceOwnerPasswordAuthenticationConverter())
    );
  }

  @Override
  public void postBuild(HttpSecurity http) {
    AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
    OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
    OAuth2TokenGenerator<?> tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);
    http.authenticationProvider(
      new OAuth2ResourceOwnerPasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator)
    );
  }
}
