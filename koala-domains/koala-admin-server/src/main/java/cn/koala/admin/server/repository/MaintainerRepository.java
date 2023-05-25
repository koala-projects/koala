package cn.koala.admin.server.repository;

import cn.koala.admin.server.Maintainer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * 运维工程师仓库接口
 *
 * @author Houtaroy
 */
public interface MaintainerRepository extends ReactiveMongoRepository<Maintainer, String> {
}
