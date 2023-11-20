package cn.koala.security.authentication.event;

import cn.koala.persist.CrudService;

/**
 * 登录日志服务接口
 *
 * @author Houtaroy
 */
public interface LoginLogService extends CrudService<LoginLog, Long> {
}
