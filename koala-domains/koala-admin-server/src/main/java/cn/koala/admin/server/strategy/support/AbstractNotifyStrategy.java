package cn.koala.admin.server.strategy.support;

import cn.koala.admin.server.Maintainer;
import cn.koala.admin.server.MessageFactory;
import cn.koala.admin.server.strategy.NotifyStrategy;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;

/**
 * 通知策略抽象类
 * <p>
 * 内置了消息工厂
 *
 * @author Houtaroy
 */
public abstract class AbstractNotifyStrategy implements NotifyStrategy {

  protected final MessageFactory messageFactory;

  public AbstractNotifyStrategy(MessageFactory messageFactory) {
    this.messageFactory = messageFactory;
  }

  @Override
  public boolean notify(Maintainer maintainer, Instance instance, InstanceEvent event) {
    return doNotify(maintainer, messageFactory.newMessage(instance, event));
  }

  protected abstract boolean doNotify(Maintainer maintainer, String message);
}
