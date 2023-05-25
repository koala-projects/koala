package cn.koala.admin.server.support;

import cn.koala.admin.server.MessageFactory;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.domain.values.StatusInfo;

import java.util.Optional;

/**
 * 简易消息工厂实现
 * <p>
 * 消息内容示例: 应用[koala-demo]状态变更: [OFFLINE]
 *
 * @author Houtaroy
 */
public class SimpleStatusChangeMessageFactory implements MessageFactory {

  private static final String TEMPLATE = "应用[%s]状态变更: [%s]";
  private static final String UNKNOWN_STATUS = "未知";

  @Override
  public String newMessage(Instance instance, InstanceEvent event) {
    return TEMPLATE.formatted(instance.getRegistration().getName(), obtainStatus(event));
  }

  protected String obtainStatus(InstanceEvent event) {
    return Optional.of(event)
      .filter(data -> event instanceof InstanceStatusChangedEvent)
      .map(InstanceStatusChangedEvent.class::cast)
      .map(InstanceStatusChangedEvent::getStatusInfo)
      .map(StatusInfo::getStatus)
      .orElse(UNKNOWN_STATUS);
  }
}
