package cn.koala.security.log.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.security.log.AuthenticateLog;

/**
 * 认证日志仓库
 *
 * @author Houtaroy
 */
public interface AuthenticateLogRepository extends CrudRepository<AuthenticateLog, Long> {
}
