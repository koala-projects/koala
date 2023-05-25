package cn.koala.admin.server.strategy;

import cn.koala.admin.server.Maintainer;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;

/**
 * 备用通知策略接口
 *
 * @author Houtaroy
 */
public interface FallbackStrategy extends NotifyStrategy {

  Maintainer getMaintainer();

  boolean notify(Instance instance, InstanceEvent event);
}
