package cn.koala.query.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.query.Query;

/**
 * 查询仓库接口
 *
 * @author Houtaroy
 */
public interface QueryRepository extends CrudRepository<Query, Long> {
}
