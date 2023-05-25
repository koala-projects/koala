package cn.koala.admin.server.repository;

import cn.koala.admin.server.Maintenance;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * 运维关系仓库接口
 *
 * @author Houtaroy
 */
public interface MaintenanceRepository extends ReactiveMongoRepository<Maintenance, String> {
  Flux<Maintenance> findByApplicationId(String applicationId);
}
