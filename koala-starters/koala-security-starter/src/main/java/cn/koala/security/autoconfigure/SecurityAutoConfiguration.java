package cn.koala.security.autoconfigure;

import cn.koala.security.AuthoritiesOpaqueTokenIntrospector;
import cn.koala.security.AuthorizationServerCustomizer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.util.List;

/**
 * Spring Authorization Server自动配置
 *
 * @author Houtaroy
 */
@Import({SecurityBeanAutoConfiguration.class, JwtAutoConfiguration.class})
@EnableConfigurationProperties({JwtProperties.class, SecurityProperties.class})
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityAutoConfiguration {
  protected final SecurityProperties properties;

  @Bean
  @Order(1)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http, List<AuthorizationServerCustomizer> customizers) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
    http.exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));
    http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    customizers.forEach(customizer -> customizer.customize(http));
    return http.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, List<SecurityFilterChainConfigurer> configurers)
    throws Exception {
    addDefaultConfigurers(configurers);
    http.csrf().disable();
    for (SecurityFilterChainConfigurer configurer : configurers) {
      configurer.configure(http);
    }
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
    http.formLogin().loginPage("/login").permitAll();
    logoutSuccessHandlerCustomizer(http);
    http.oauth2ResourceServer().opaqueToken();
    return http.build();
  }

  protected void addDefaultConfigurers(List<SecurityFilterChainConfigurer> configurers) {
    configurers.add(new PermitAllConfigurer(List.of("/swagger*/**", "/v3/api-docs/**")));
    configurers.add(new PermitAllConfigurer(List.of("/login/**")));
    configurers.add(new PermitAllConfigurer(properties.getPermitAllPatterns()));
  }

  protected void logoutSuccessHandlerCustomizer(HttpSecurity http) throws Exception {
    SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
    logoutSuccessHandler.setTargetUrlParameter("redirect_uri");
    http.logout().logoutSuccessHandler(logoutSuccessHandler);
  }

  @Bean
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }

  @Bean
  public OpaqueTokenIntrospector introspector(OAuth2ResourceServerProperties properties) {
    OAuth2ResourceServerProperties.Opaquetoken opaquetoken = properties.getOpaquetoken();
    return new AuthoritiesOpaqueTokenIntrospector(
      opaquetoken.getIntrospectionUri(),
      opaquetoken.getClientId(),
      opaquetoken.getClientSecret()
    );
  }
}
