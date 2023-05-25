package cn.koala.admin.server.strategy.support;

import cn.koala.admin.server.Maintainer;
import cn.koala.admin.server.strategy.FallbackStrategy;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * 备用通知策略抽象类
 * <p>
 * 需要手动提供一个备用运维工程师
 *
 * @author Houtaroy
 */
@Getter
public abstract class AbstractFallbackStrategy implements FallbackStrategy {

  protected final Maintainer maintainer;

  public AbstractFallbackStrategy(Maintainer maintainer) {
    Assert.notNull(maintainer, "备用运维工程师不能为空");
    this.maintainer = maintainer;
  }

  @Override
  public String getName() {
    return "fallback";
  }

  @Override
  public boolean notify(Instance instance, InstanceEvent event) {
    return notify(getMaintainer(), instance, event);
  }
}
