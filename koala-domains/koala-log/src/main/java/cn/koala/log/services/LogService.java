package cn.koala.log.services;

import cn.koala.log.Log;
import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;

/**
 * 日志服务
 *
 * @author Houtaroy
 */
public interface LogService extends CrudService<Log, Long>, PagingService<Log, Long> {
}
