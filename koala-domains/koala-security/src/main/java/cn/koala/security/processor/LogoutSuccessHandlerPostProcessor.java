package cn.koala.security.processor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * 登出成功拦截器后置处理器
 *
 * @author Houtaroy
 */
public class LogoutSuccessHandlerPostProcessor implements DefaultSecurityPostProcessor {
  @Override
  public void postProcessBeforeInitialization(HttpSecurity http) throws Exception {
    SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
    logoutSuccessHandler.setTargetUrlParameter("redirect_uri");
    http.logout().logoutSuccessHandler(logoutSuccessHandler);
  }

  @Override
  public void postProcessAfterInitialization(HttpSecurity http) {

  }
}
