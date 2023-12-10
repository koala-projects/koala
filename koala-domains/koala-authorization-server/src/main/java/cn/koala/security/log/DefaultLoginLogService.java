package cn.koala.security.log;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.security.log.repository.LoginLogRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 默认登录日志服务
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultLoginLogService extends AbstractMyBatisService<LoginLog, Long> implements LoginLogService {

  private final LoginLogRepository repository;
}
