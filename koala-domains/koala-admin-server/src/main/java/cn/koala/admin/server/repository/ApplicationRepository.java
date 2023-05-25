package cn.koala.admin.server.repository;

import cn.koala.admin.server.Application;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * 应用仓库接口
 *
 * @author Houtaroy
 */
public interface ApplicationRepository extends ReactiveMongoRepository<Application, String> {
  Mono<Application> findByName(String name);
}
