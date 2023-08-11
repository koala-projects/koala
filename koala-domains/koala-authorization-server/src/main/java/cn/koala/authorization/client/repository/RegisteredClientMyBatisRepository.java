package cn.koala.authorization.client.repository;

import cn.koala.authorization.client.RegisteredClientEntity;
import cn.koala.persist.repository.CrudRepository;

/**
 * 注册客户端Mybatis仓库接口
 *
 * @author Houtaroy
 */
public interface RegisteredClientMyBatisRepository extends CrudRepository<RegisteredClientEntity, String> {

}
