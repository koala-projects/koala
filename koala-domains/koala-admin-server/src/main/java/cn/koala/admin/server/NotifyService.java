package cn.koala.admin.server;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import reactor.core.publisher.Mono;

/**
 * 通知服务接口
 *
 * @author Houtaroy
 */
public interface NotifyService {
  Mono<Void> notify(Instance instance, InstanceEvent event);
}
