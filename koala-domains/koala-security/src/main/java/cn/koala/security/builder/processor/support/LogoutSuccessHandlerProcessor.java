package cn.koala.security.builder.processor.support;

import cn.koala.security.builder.processor.ResourceServerProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * 登出成功拦截器处理器
 *
 * @author Houtaroy
 */
@Order(5000)
public class LogoutSuccessHandlerProcessor implements ResourceServerProcessor {

  @Override
  public void preBuild(HttpSecurity http) throws Exception {
    SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
    logoutSuccessHandler.setTargetUrlParameter("redirect_uri");
    http.logout().logoutSuccessHandler(logoutSuccessHandler);
  }

  @Override
  public void postBuild(HttpSecurity http) {

  }
}
