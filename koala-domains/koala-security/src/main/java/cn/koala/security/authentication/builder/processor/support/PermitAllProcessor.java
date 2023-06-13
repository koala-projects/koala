package cn.koala.security.authentication.builder.processor.support;

import cn.koala.security.authentication.builder.processor.ResourceServerProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.ObjectUtils;

/**
 * 权限校验放开处理器抽象类
 *
 * @author Houtaroy
 */
public class PermitAllProcessor implements ResourceServerProcessor {

  private final String[] patterns;

  public PermitAllProcessor(String... patterns) {
    this.patterns = patterns;
  }

  @Override
  public void preBuild(HttpSecurity http) throws Exception {
    if (!ObjectUtils.isEmpty(this.patterns)) {
      http.authorizeHttpRequests().requestMatchers(this.patterns).permitAll();
    }
  }

  @Override
  public void postBuild(HttpSecurity http) {

  }
}
