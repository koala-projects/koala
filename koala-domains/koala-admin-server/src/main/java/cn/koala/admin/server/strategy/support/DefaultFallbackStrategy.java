package cn.koala.admin.server.strategy.support;

import cn.koala.admin.server.Maintainer;
import cn.koala.admin.server.strategy.NotifyStrategy;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import org.springframework.util.Assert;

/**
 * 默认备用策略
 * <p>
 * 基于企业微信通知策略
 *
 * @author Houtaroy
 */
public class DefaultFallbackStrategy extends AbstractFallbackStrategy {

  private final NotifyStrategy weworkStrategy;

  public DefaultFallbackStrategy(Maintainer maintainer, NotifyStrategy weworkStrategy) {
    super(maintainer);
    Assert.isTrue(weworkStrategy instanceof WeworkStrategy, "默认备用策略需开启企业微信策略");
    this.weworkStrategy = weworkStrategy;
  }

  @Override
  public boolean notify(Maintainer maintainer, Instance instance, InstanceEvent event) {
    return this.weworkStrategy.notify(maintainer, instance, event);
  }
}
