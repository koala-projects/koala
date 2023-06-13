package cn.koala.security.authentication.builder.processor.support;

import cn.koala.security.authentication.builder.processor.AuthorizationServerProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 认证服务处理器
 *
 * @author Houtaroy
 */
@Order(1000)
public class OAuth2AuthorizationServerProcessor implements AuthorizationServerProcessor {

  @Override
  public void preBuild(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
    http.exceptionHandling(
      (exceptions) -> exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
    );
    http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
  }

  @Override
  public void postBuild(HttpSecurity http) {

  }
}
