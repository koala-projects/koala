package cn.koala.admin.server.support;

import cn.koala.admin.server.NotifyService;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * 默认通知器
 * <p>
 * 用于替换Spring Boot Admin原有通知器
 *
 * @author Houtaroy
 */
public class DefaultNotifier extends AbstractStatusChangeNotifier {

  private final NotifyService service;


  public DefaultNotifier(InstanceRepository repository, NotifyService service) {
    super(repository);
    this.service = service;
  }

  @Override
  @NonNull
  protected Mono<Void> doNotify(@NonNull InstanceEvent event, @NonNull Instance instance) {
    return service.notify(instance, event);
  }
}
