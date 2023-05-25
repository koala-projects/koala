package cn.koala.admin.server;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;

/**
 * 消息工厂
 * <p>
 * 根据实例和事件生成消息内容
 *
 * @author Houtaroy
 */
public interface MessageFactory {
  String newMessage(Instance instance, InstanceEvent event);
}
