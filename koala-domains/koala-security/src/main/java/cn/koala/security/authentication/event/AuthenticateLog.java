package cn.koala.security.authentication.event;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.YesNo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Date;

/**
 * 认证日志
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AuthenticateLog implements Persistable<Long> {

  protected Long id;

  protected String remoteAddress;

  protected String sessionId;

  protected String authentication;

  protected YesNo isSuccessful;

  protected String exceptionMessage;

  protected Date logTime;

  public static AuthenticateLog from(AbstractAuthenticationEvent event) {
    AuthenticateLog result = new AuthenticateLog();
    result.setAuthenticationDetails(event.getAuthentication().getDetails());
    result.setStatus(event);
    result.setLogTime(new Date(event.getTimestamp()));
    return result;
  }

  protected void setAuthenticationDetails(Object details) {
    if (details instanceof WebAuthenticationDetails webAuthenticationDetails) {
      setWebAuthenticationDetails(webAuthenticationDetails);
    }
  }

  protected void setWebAuthenticationDetails(WebAuthenticationDetails details) {
    setRemoteAddress(details.getRemoteAddress());
    setSessionId(details.getSessionId());
  }

  protected void setStatus(AbstractAuthenticationEvent event) {
    if (event instanceof AbstractAuthenticationFailureEvent failureEvent) {
      setIsSuccessful(YesNo.NO);
      setExceptionMessage(failureEvent.getException().getMessage());
    } else {
      setIsSuccessful(YesNo.YES);
    }
  }
}
