package cn.koala.security.authentication.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;

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
    if (event.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
      LoginLog log = LoginLog.from(event);
      service.create(log);
    }
  }
}
