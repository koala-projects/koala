package cn.koala.security.authentication.event;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.YesNo;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 登录日志
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class LoginLog implements Persistable<Long> {

  private Long id;

  private String remoteAddress;

  private String sessionId;

  private Long userId;

  private String username;

  private String password;

  private YesNo isSuccessful;

  private String exceptionMessage;

  private Date logTime;
}
