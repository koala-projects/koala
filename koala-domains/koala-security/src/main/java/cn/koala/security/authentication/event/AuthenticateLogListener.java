package cn.koala.security.authentication.event;

import cn.koala.persist.domain.YesNo;
import cn.koala.util.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Date;

/**
 * 认证日志监听器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class AuthenticateLogListener {

  private final ObjectMapper objectMapper;
  private final AuthenticateLogService service;

  @EventListener
  public void onSuccess(AuthenticationSuccessEvent event) {
    AuthenticateLog log = new AuthenticateLog();
    if (event.getAuthentication().getDetails() instanceof WebAuthenticationDetails details) {
      log.setRemoteAddress(details.getRemoteAddress());
      log.setSessionId(details.getSessionId());
    }
    log.setAuthentication(JacksonUtils.writeValueAsStringQuietly(objectMapper, event.getAuthentication()));
    log.setIsSuccessful(YesNo.YES);
    log.setLogTime(new Date(event.getTimestamp()));
    service.create(log);
  }

  @EventListener
  public void onFailure(AbstractAuthenticationFailureEvent event) {
    AuthenticateLog log = new AuthenticateLog();
    if (event.getAuthentication().getDetails() instanceof WebAuthenticationDetails details) {
      log.setRemoteAddress(details.getRemoteAddress());
      log.setSessionId(details.getSessionId());
    }
    log.setAuthentication(JacksonUtils.writeValueAsStringQuietly(objectMapper, event.getAuthentication()));
    log.setIsSuccessful(YesNo.NO);
    log.setExceptionMessage(event.getException().getLocalizedMessage());
    log.setLogTime(new Date(event.getTimestamp()));
    service.create(log);
  }
}
