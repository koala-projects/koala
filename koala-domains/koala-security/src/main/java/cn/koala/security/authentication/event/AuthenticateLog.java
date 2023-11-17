package cn.koala.security.authentication.event;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.YesNo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

  private Long id;

  private String remoteAddress;

  private String sessionId;

  private String authentication;

  private YesNo isSuccessful;

  private String exceptionMessage;

  private Date logTime;
}
