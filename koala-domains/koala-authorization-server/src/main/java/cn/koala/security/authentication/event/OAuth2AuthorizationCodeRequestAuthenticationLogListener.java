package cn.koala.security.authentication.event;

import cn.koala.util.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;

/**
 * 授权码请求日志监听器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class OAuth2AuthorizationCodeRequestAuthenticationLogListener {

  private final ObjectMapper objectMapper;
  private final AuthenticateLogService service;

  @EventListener
  public void onEvent(AbstractAuthenticationEvent event) {
    if (event.getAuthentication() instanceof OAuth2AuthorizationCodeRequestAuthenticationToken) {
      AuthenticateLog log = AuthenticateLog.from(event);
      log.setAuthentication(JacksonUtils.writeValueAsStringQuietly(objectMapper, event.getAuthentication()));
      service.create(log);
    }
  }
}
