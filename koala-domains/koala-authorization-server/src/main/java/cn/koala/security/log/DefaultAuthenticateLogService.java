package cn.koala.security.log;

import cn.koala.mybatis.service.AbstractCrudService;
import cn.koala.security.log.repository.AuthenticateLogRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 默认认证日志服务
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultAuthenticateLogService extends AbstractCrudService<AuthenticateLog, Long>
  implements AuthenticateLogService {

  private final AuthenticateLogRepository repository;
}
