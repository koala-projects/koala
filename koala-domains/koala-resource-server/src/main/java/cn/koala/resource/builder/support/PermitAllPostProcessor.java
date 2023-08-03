package cn.koala.resource.builder.support;

import cn.koala.resource.builder.ResourceServerSecurityFilterChainPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 权限校验放开处理器抽象类
 *
 * @author Houtaroy
 */
public class PermitAllPostProcessor implements ResourceServerSecurityFilterChainPostProcessor {

  private final String[] patterns;

  public PermitAllPostProcessor(String... patterns) {
    this.patterns = patterns;
  }

  public PermitAllPostProcessor(List<String> patterns) {
    this.patterns = patterns.toArray(new String[0]);
  }

  @Override
  public void postProcessBeforeBuild(HttpSecurity http) throws Exception {
    if (!ObjectUtils.isEmpty(this.patterns)) {
      http.authorizeHttpRequests().requestMatchers(this.patterns).permitAll();
    }
  }

  @Override
  public void postProcessAfterBuild(HttpSecurity http) {

  }
}
