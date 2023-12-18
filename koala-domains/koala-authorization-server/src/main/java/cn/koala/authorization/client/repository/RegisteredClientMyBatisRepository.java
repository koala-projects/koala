package cn.koala.authorization.client.repository;

import cn.koala.authorization.client.domain.RegisteredClientEntity;
import cn.koala.mybatis.repository.CrudRepository;

/**
 * 注册客户端Mybatis仓库接口
 *
 * @author Houtaroy
 */
public interface RegisteredClientMyBatisRepository extends CrudRepository<RegisteredClientEntity, String> {

}
