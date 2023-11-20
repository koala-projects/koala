package cn.koala.security.authentication.event;

import cn.koala.security.userdetails.KoalaUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;

import java.util.Date;
import java.util.Optional;

/**
 * 登录日志
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LoginLog extends AuthenticateLog {

  private Long userId;

  private String username;

  private String password;

  public static LoginLog from(AbstractAuthenticationEvent event) {
    LoginLog result = new LoginLog();
    result.setAuthenticationDetails(event.getAuthentication().getDetails());
    result.setPrincipal(event.getAuthentication().getPrincipal());
    result.setPassword(
      Optional.ofNullable(event.getAuthentication().getCredentials())
        .map(Object::toString)
        .orElse(null)
    );
    result.setStatus(event);
    result.setLogTime(new Date(event.getTimestamp()));
    return result;
  }

  private void setPrincipal(Object principal) {
    if (principal instanceof KoalaUser user) {
      setUserId(user.getId());
      setUsername(user.getUsername());
    }
    if (principal instanceof String) {
      setUsername(principal.toString());
    }
  }
}
