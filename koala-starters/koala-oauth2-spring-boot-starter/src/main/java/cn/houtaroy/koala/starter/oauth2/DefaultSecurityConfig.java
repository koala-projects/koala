package cn.houtaroy.koala.starter.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Houtaroy
 */
@Configuration
@EnableWebSecurity
@Import(AuthorizationServerConfig.class)
public class DefaultSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
      .formLogin(withDefaults()).build();
  }
}
