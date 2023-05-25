package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.NotifyService;
import cn.koala.admin.server.repository.ApplicationRepository;
import cn.koala.admin.server.repository.MaintainerRepository;
import cn.koala.admin.server.repository.MaintenanceRepository;
import cn.koala.admin.server.strategy.FallbackStrategy;
import cn.koala.admin.server.strategy.NotifyStrategy;
import cn.koala.admin.server.support.DefaultNotifier;
import cn.koala.admin.server.support.DefaultNotifyService;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Admin Server 通知自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class AdminServerNotifyAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public NotifyService notifyService(ApplicationRepository applicationRepository,
                                     MaintainerRepository maintainerRepository,
                                     MaintenanceRepository maintenanceRepository,
                                     List<NotifyStrategy> strategies,
                                     ObjectProvider<FallbackStrategy> fallbackStrategy) {
    return new DefaultNotifyService(applicationRepository, maintainerRepository, maintenanceRepository, strategies,
      fallbackStrategy.getIfAvailable());
  }

  @Bean
  public DefaultNotifier defaultNotifier(InstanceRepository instanceRepository, NotifyService notifyService) {
    return new DefaultNotifier(instanceRepository, notifyService);
  }
}
