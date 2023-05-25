package cn.koala.admin.server.support;

import cn.koala.admin.server.Maintenance;
import cn.koala.admin.server.NotifyService;
import cn.koala.admin.server.repository.ApplicationRepository;
import cn.koala.admin.server.repository.MaintainerRepository;
import cn.koala.admin.server.repository.MaintenanceRepository;
import cn.koala.admin.server.strategy.FallbackStrategy;
import cn.koala.admin.server.strategy.NotifyStrategy;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * 默认通知服务
 * <p>
 * 内部整合了通知策略和备用通知策略
 * <p>
 * 先根据应用名称寻找运维关系, 再根据关系寻找通知策略和运维工程师, 再执行通知策略
 * <p>
 * 若未找到对应的通知策略或所有通知策略均执行失败, 则执行备用通知策略
 *
 * @author Houtaroy
 */
@Slf4j
public class DefaultNotifyService implements NotifyService {

  private final ApplicationRepository applicationRepository;
  private final MaintainerRepository maintainerRepository;
  private final MaintenanceRepository maintenanceRepository;
  private final Map<String, NotifyStrategy> strategies;
  private final FallbackStrategy fallbackStrategy;

  public DefaultNotifyService(ApplicationRepository applicationRepository, MaintainerRepository maintainerRepository,
                              MaintenanceRepository maintenanceRepository, List<NotifyStrategy> strategies,
                              FallbackStrategy fallbackStrategy) {
    this.applicationRepository = applicationRepository;
    this.maintainerRepository = maintainerRepository;
    this.maintenanceRepository = maintenanceRepository;
    if (CollectionUtils.isEmpty(strategies)) {
      this.strategies = new ConcurrentReferenceHashMap<>();
    } else {
      this.strategies = new ConcurrentReferenceHashMap<>(strategies.size());
      strategies.forEach(this::addStrategy);
    }
    this.fallbackStrategy = fallbackStrategy;
  }

  protected void addStrategy(NotifyStrategy strategy) {
    if (this.strategies.containsKey(strategy.getName())) {
      LOGGER.warn("[koala-admin-server]: 通知策略[{}]重复, 已自动覆盖", strategy.getName());
    }
    this.strategies.put(strategy.getName(), strategy);
  }

  @Override
  public Mono<Void> notify(Instance instance, InstanceEvent event) {
    return this.applicationRepository.findByName(instance.getRegistration().getName())
      .flatMapMany(application -> maintenanceRepository.findByApplicationId(application.getId()))
      .flatMap(maintenance -> notify(maintenance, instance, event))
      .any(success -> success)
      .flatMap(anySuccess -> {
        if (!anySuccess) {
          LOGGER.warn("[koala-admin-server]: 所有配置通知策略执行失败, 执行备用通知策略");
          if (!this.fallback(instance, event)) {
            LOGGER.error("[koala-admin-server]: 备用通知策略执行失败");
          }
        }
        return Mono.empty();
      });
  }

  protected Mono<Boolean> notify(Maintenance maintenance, Instance instance, InstanceEvent event) {
    String strategyName = maintenance.getStrategy();
    NotifyStrategy strategy = this.strategies.get(strategyName);
    Assert.notNull(strategy, "[koala-admin-server]: 未找到指定策略[名称=%s]".formatted(strategyName));
    return this.maintainerRepository.findById(maintenance.getMaintainerId())
      .map(maintainer -> strategy.notify(maintainer, instance, event));
  }

  protected boolean fallback(Instance instance, InstanceEvent event) {
    if (this.fallbackStrategy == null) {
      LOGGER.warn("[koala-admin-server]: 没有备用运维策略");
      return false;
    }
    return this.fallbackStrategy.notify(instance, event);
  }
}
