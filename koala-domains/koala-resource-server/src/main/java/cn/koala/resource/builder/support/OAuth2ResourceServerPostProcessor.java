package cn.koala.resource.builder.support;

import cn.koala.resource.builder.ResourceServerSecurityFilterChainPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 资源服务处理器
 *
 * @author Houtaroy
 */
@Order(3900)
public class OAuth2ResourceServerPostProcessor implements ResourceServerSecurityFilterChainPostProcessor {


  @Override
  public void postProcessBeforeBuild(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
    http.oauth2ResourceServer().opaqueToken();
  }

  @Override
  public void postProcessAfterBuild(HttpSecurity http) {

  }
}
