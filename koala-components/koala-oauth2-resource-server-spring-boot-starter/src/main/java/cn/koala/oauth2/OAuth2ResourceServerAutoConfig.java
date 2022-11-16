package cn.koala.oauth2;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * OAuth2资源服务自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
@EnableConfigurationProperties(ResourceServerProperties.class)
@SuppressWarnings("PMD")
public class OAuth2ResourceServerAutoConfig {

  /**
   * 授权服务器默认安全过滤器链配置
   *
   * @param http       HttpSecurity对象
   * @param properties 资源服务配置
   * @param jwtDecoder JwtDecoder
   * @return 安全过滤器链实例
   * @throws Exception 异常
   */
  @Bean
  @Order(200)
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, ResourceServerProperties properties,
                                                 JwtDecoder jwtDecoder) throws Exception {
    http.authorizeRequests().antMatchers("/**/*swagger*/**", "/**/api-doc*").permitAll();
    http.authorizeRequests().antMatchers(properties.getPermitAllAntPatterns().toArray(new String[0])).permitAll();
    http.authorizeRequests().anyRequest().authenticated();
    http.formLogin(Customizer.withDefaults());
    http.logout(Customizer.withDefaults());
    http.csrf().disable();
    http.oauth2ResourceServer(new OAuth2ResourceServerConfigurerCustomizer(jwtDecoder));
    return http.build();
  }
}
