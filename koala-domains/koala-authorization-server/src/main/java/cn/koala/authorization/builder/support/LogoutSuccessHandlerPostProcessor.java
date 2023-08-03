package cn.koala.authorization.builder.support;

import cn.koala.authorization.builder.DefaultSecurityFilterChainPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * 登出成功拦截器处理器
 *
 * @author Houtaroy
 */
@Order(3200)
public class LogoutSuccessHandlerPostProcessor implements DefaultSecurityFilterChainPostProcessor {

  @Override
  public void postProcessBeforeBuild(HttpSecurity http) throws Exception {
    SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
    logoutSuccessHandler.setTargetUrlParameter("redirect_uri");
    http.logout().logoutSuccessHandler(logoutSuccessHandler);
  }

  @Override
  public void postProcessAfterBuild(HttpSecurity http) {

  }
}
