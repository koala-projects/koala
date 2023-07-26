package cn.koala.log.repositories;

import cn.koala.log.Log;
import cn.koala.persist.repository.CrudRepository;

/**
 * 日志仓库接口
 *
 * @author Houtaroy
 */
public interface LogRepository extends CrudRepository<Log, Long> {
}
