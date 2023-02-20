package cn.koala.security.autoconfigure;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * PermitAll配置类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class PermitAllConfigurer implements SecurityFilterChainConfigurer {
  private final List<String> patterns;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    if (!CollectionUtils.isEmpty(patterns)) {
      http.authorizeHttpRequests().requestMatchers(patterns.toArray(new String[0])).permitAll();
    }
  }
}
