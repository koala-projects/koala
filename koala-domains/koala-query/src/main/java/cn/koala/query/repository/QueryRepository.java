package cn.koala.query.repository;


import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.query.domain.Query;

/**
 * 查询仓库接口
 *
 * @author Houtaroy
 */
public interface QueryRepository extends CrudRepository<Query, Long> {
}
