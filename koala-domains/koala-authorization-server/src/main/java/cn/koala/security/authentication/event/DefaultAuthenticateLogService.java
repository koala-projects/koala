package cn.koala.security.authentication.event;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.security.authentication.event.repository.AuthenticateLogRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 默认认证日志服务
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultAuthenticateLogService extends AbstractMyBatisService<AuthenticateLog, Long>
  implements AuthenticateLogService {

  private final AuthenticateLogRepository repository;
}
