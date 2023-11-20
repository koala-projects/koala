package cn.koala.security.authentication.event.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.security.authentication.event.LoginLog;

/**
 * 登录日志仓库
 *
 * @author Houtaroy
 */
public interface LoginLogRepository extends CrudRepository<LoginLog, Long> {
}
