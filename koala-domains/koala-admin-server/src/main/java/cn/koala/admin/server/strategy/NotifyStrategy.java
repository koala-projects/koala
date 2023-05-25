package cn.koala.admin.server.strategy;

import cn.koala.admin.server.Maintainer;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;

/**
 * 通知策略接口
 *
 * @author Houtaroy
 */
public interface NotifyStrategy {

  String getName();

  boolean notify(Maintainer maintainer, Instance instance, InstanceEvent event);
}
