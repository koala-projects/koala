package cn.koala.security.autoconfigure;

import cn.koala.security.CustomAuthoritiesOpaqueTokenIntrospector;
import lombok.RequiredArgsConstructor;
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
  protected final List<SecurityFilterChainConfigurer> configurers;

  @Bean
  @Order(1)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
    throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
      .oidc(Customizer.withDefaults());  // Enable OpenID Connect 1.0
    http
      // Redirect to the login page when not authenticated from the
      // authorization endpoint
      .exceptionHandling((exceptions) -> exceptions
        .authenticationEntryPoint(
          new LoginUrlAuthenticationEntryPoint("/login"))
      )
      // Accept access tokens for User Info and/or Client Registration
      .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    return http.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
    throws Exception {
    http.csrf().disable();
    configurers.add(new PermitAllConfigurer(List.of("/swagger*/**", "/v3/api-docs/**")));
    configurers.add(new PermitAllConfigurer(List.of("/login/**")));
    configurers.add(new PermitAllConfigurer(properties.getPermitAllPatterns()));
    for (SecurityFilterChainConfigurer configurer : configurers) {
      configurer.configure(http);
    }
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
    http.formLogin().loginPage("/login").permitAll();
    http.oauth2ResourceServer().opaqueToken();
    return http.build();
  }

  @Bean
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }

  @Bean
  public OpaqueTokenIntrospector introspector() {
    return new CustomAuthoritiesOpaqueTokenIntrospector();
  }
}
