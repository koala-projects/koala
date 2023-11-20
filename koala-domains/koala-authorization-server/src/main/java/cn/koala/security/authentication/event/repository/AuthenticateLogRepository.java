package cn.koala.security.authentication.event.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.security.authentication.event.AuthenticateLog;

/**
 * 认证日志仓库
 *
 * @author Houtaroy
 */
public interface AuthenticateLogRepository extends CrudRepository<AuthenticateLog, Long> {
}
