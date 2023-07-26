package cn.koala.database.repositories;

import cn.koala.database.Database;
import cn.koala.persist.repository.CrudRepository;

/**
 * 数据库仓库接口
 *
 * @author Houtaroy
 */
public interface DatabaseRepository extends CrudRepository<Database, Long> {
}
