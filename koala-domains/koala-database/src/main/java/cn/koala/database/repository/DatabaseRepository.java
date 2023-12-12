package cn.koala.database.repository;

import cn.koala.database.domain.Database;
import cn.koala.mybatis.repository.CrudRepository;

/**
 * 数据库仓库接口
 *
 * @author Houtaroy
 */
public interface DatabaseRepository extends CrudRepository<Database, Long> {
}
