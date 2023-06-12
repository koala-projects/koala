package cn.koala.log.services;

import cn.koala.log.Log;
import cn.koala.log.repositories.LogRepository;
import cn.koala.mybatis.AbstractMyBatisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 日志服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class LogServiceImpl extends AbstractMyBatisService<Log, Long> implements LogService {

  protected final LogRepository repository;
}
