package cn.koala.authorization.builder.support;

import cn.koala.authorization.builder.DefaultSecurityFilterChainPostProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 表单登录附加处理器
 *
 * @author Houtaroy
 */
@Order(3100)
@RequiredArgsConstructor
public class FormLoginPostProcessor implements DefaultSecurityFilterChainPostProcessor {

  private final boolean customLoginPage;

  @Override
  public void postProcessBeforeBuild(HttpSecurity http) throws Exception {
    http.formLogin(this.customLoginPage ? form -> form.loginPage("/login") : Customizer.withDefaults());
  }

  @Override
  public void postProcessAfterBuild(HttpSecurity http) {

  }
}
