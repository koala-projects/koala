package cn.koala.security.autoconfigure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 安全过滤连配置接口
 *
 * @author Houtaroy
 */
public interface SecurityFilterChainConfigurer {
  void configure(HttpSecurity http) throws Exception;
}
