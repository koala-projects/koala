package cn.koala.log.services;

import cn.koala.log.Log;
import cn.koala.log.repositories.LogRepository;
import cn.koala.mybatis.BaseMyBatisService;

/**
 * 日志服务实现类
 *
 * @author Houtaroy
 */
public class LogServiceImpl extends BaseMyBatisService<Log, Long> implements LogService {
  public LogServiceImpl(LogRepository repository) {
    super(repository);
  }
}
