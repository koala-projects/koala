package cn.koala.system.autoconfigure;

import cn.koala.security.autoconfigure.SecurityFilterChainConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 系统管理安全过滤器配置类
 *
 * @author Houtaroy
 */
public class SystemSecurityFilterChainConfigurer implements SecurityFilterChainConfigurer {
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/api/settings/{*id}").permitAll();
  }
}
