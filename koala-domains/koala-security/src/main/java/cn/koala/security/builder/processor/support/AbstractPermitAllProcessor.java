package cn.koala.security.builder.processor.support;

import cn.koala.security.builder.processor.ResourceServerProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.ObjectUtils;

/**
 * 权限校验放开处理器抽象类
 *
 * @author Houtaroy
 */
public abstract class AbstractPermitAllProcessor implements ResourceServerProcessor {

  private final String[] patterns;

  public AbstractPermitAllProcessor(String... patterns) {
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
