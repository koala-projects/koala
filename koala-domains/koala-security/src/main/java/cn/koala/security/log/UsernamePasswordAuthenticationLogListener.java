package cn.koala.security.log;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

/**
 * 用户名密码认证监听器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationLogListener {

  private final LoginLogService service;

  @EventListener
  public void onEvent(AbstractAuthenticationEvent event) {
    if (event instanceof AuthenticationSuccessEvent || event instanceof AbstractAuthenticationFailureEvent) {
      if (event.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
        LoginLog log = LoginLog.from(event);
        service.create(log);
      }
    }
  }
}
