package cn.koala.authorization.builder.support;

import cn.koala.authorization.builder.AuthorizationServerSecurityFilterChainPostProcessor;
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
@Order(3100)
public class OAuth2AuthorizationServerPostProcessor implements AuthorizationServerSecurityFilterChainPostProcessor {

  @Override
  public void postProcessBeforeBuild(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    OAuth2AuthorizationServerConfigurer configurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
    configurer.oidc(Customizer.withDefaults());
    configurer.oidc(oidc -> oidc.clientRegistrationEndpoint(Customizer.withDefaults()));
    http.exceptionHandling(
      (exceptions) -> exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
    );
    http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
  }

  @Override
  public void postProcessAfterBuild(HttpSecurity http) {

  }
}
