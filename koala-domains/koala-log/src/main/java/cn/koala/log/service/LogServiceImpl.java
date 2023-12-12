package cn.koala.log.service;

import cn.koala.log.Log;
import cn.koala.log.repository.LogRepository;
import cn.koala.mybatis.service.AbstractCrudService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 日志服务实现类
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class LogServiceImpl extends AbstractCrudService<Log, Long> implements LogService {

  protected final LogRepository repository;
}
