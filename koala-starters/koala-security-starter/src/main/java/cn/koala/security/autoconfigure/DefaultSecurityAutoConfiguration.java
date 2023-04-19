package cn.koala.security.autoconfigure;

import cn.koala.security.SecurityProperties;
import cn.koala.security.processor.DefaultSecurityPostProcessor;
import cn.koala.security.processor.LogoutSuccessHandlerPostProcessor;
import cn.koala.security.processor.PermitAllPostProcessor;
import cn.koala.security.repositories.UserDetailsRepository;
import cn.koala.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * 默认安全自动配置
 *
 * @author Houtaroy
 */
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class DefaultSecurityAutoConfiguration {
  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, List<DefaultSecurityPostProcessor> processors)
    throws Exception {
    for (DefaultSecurityPostProcessor processor : processors) {
      processor.postProcessBeforeInitialization(http);
    }
    http.csrf().disable();
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
    http.formLogin().loginPage("/login").permitAll();
    http.oauth2ResourceServer().opaqueToken();
    SecurityFilterChain result = http.build();
    for (DefaultSecurityPostProcessor processor : processors) {
      processor.postProcessAfterInitialization(http);
    }
    return result;
  }

  @Bean
  public DefaultSecurityPostProcessor defaultPermitAllPostProcessor() {
    return new PermitAllPostProcessor(List.of("/login/**", "/swagger*/**", "/v3/api-docs/**"));
  }

  @Bean
  public DefaultSecurityPostProcessor customPermitAllPostProcessor(SecurityProperties properties) {
    return new PermitAllPostProcessor(properties.getPermitAllPatterns());
  }

  @Bean
  public DefaultSecurityPostProcessor logoutSuccessHandlerPostProcessor() {
    return new LogoutSuccessHandlerPostProcessor();
  }

  @Bean
  public UserDetailsService userDetailsService(UserDetailsRepository userDetailsRepository) {
    return new UserDetailsServiceImpl(userDetailsRepository);
  }
}
