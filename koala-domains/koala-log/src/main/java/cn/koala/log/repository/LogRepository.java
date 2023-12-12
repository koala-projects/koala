package cn.koala.log.repository;

import cn.koala.log.Log;
import cn.koala.mybatis.repository.CrudRepository;

/**
 * 日志仓库接口
 *
 * @author Houtaroy
 */
public interface LogRepository extends CrudRepository<Log, Long> {
}
