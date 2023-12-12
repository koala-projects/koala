package cn.koala.security.log.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.security.log.LoginLog;

/**
 * 登录日志仓库
 *
 * @author Houtaroy
 */
public interface LoginLogRepository extends CrudRepository<LoginLog, Long> {
}
