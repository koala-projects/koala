package cn.koala.security.authentication.builder.processor.support;

import cn.koala.security.authentication.builder.processor.ResourceServerProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 资源服务处理器
 *
 * @author Houtaroy
 */
@Order(4000)
public class OAuth2ResourceServerProcessor implements ResourceServerProcessor {
  @Override
  public void preBuild(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
    http.formLogin().loginPage("/login").permitAll();
    http.oauth2ResourceServer().opaqueToken();
  }

  @Override
  public void postBuild(HttpSecurity http) {

  }
}
