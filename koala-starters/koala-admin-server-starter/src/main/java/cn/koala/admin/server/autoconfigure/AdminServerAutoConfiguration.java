package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.NotifyService;
import cn.koala.admin.server.api.ApplicationApi;
import cn.koala.admin.server.api.MaintainerApi;
import cn.koala.admin.server.api.MaintenanceApi;
import cn.koala.admin.server.api.NotifyStrategyApi;
import cn.koala.admin.server.repository.ApplicationRepository;
import cn.koala.admin.server.repository.MaintainerRepository;
import cn.koala.admin.server.repository.MaintenanceRepository;
import cn.koala.admin.server.strategy.NotifyStrategy;
import cn.koala.admin.server.strategy.NotifyStrategyService;
import cn.koala.admin.server.strategy.support.InMemoryNotifyStrategyService;
import cn.koala.admin.server.strategy.support.MailNotifyStrategy;
import cn.koala.admin.server.support.DefaultNotifier;
import cn.koala.admin.server.support.DefaultNotifyService;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.MailNotifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.util.List;

/**
 * Admin Server自动配置
 * <p>
 * 自动装配管理接口
 *
 * @author Houtaroy
 */
@Configuration
@AutoConfigureAfter(MailSenderAutoConfiguration.class)
@EnableConfigurationProperties(AdminServerProperties.class)
@EnableAdminServer
@EnableMongoRepositories(basePackages = "cn.koala.admin.server.repository")
public class AdminServerAutoConfiguration {

  @Bean
  @ConditionalOnBean(MailSender.class)
  @ConditionalOnMissingBean(name = "mailNotifyStrategy")
  public MailNotifyStrategy mailNotifyStrategy(MailSender mailSender, AdminServerProperties properties) {
    return new MailNotifyStrategy(mailSender, properties.getMail().getFrom(), properties.getMail().getSubject());
  }

  @Bean
  @ConditionalOnBean({MailSender.class, MailNotifyStrategy.class})
  @ConfigurationProperties("spring.boot.admin.notify.mail")
  public MailNotifier mailNotifier(JavaMailSender mailSender, InstanceRepository repository,
                                   TemplateEngine mailNotifierTemplateEngine) {
    MailNotifier result = new MailNotifier(mailSender, repository, mailNotifierTemplateEngine);
    result.setEnabled(false);
    return result;
  }

  @Bean
  @ConditionalOnMissingBean
  public NotifyStrategyService notifyStrategyService(List<NotifyStrategy> strategies) {
    return new InMemoryNotifyStrategyService(strategies);
  }

  @Bean
  @ConditionalOnMissingBean
  public NotifyService notifyService(ApplicationRepository applicationRepository,
                                     MaintainerRepository maintainerRepository,
                                     MaintenanceRepository maintenanceRepository,
                                     NotifyStrategyService notifyStrategyService,
                                     AdminServerProperties properties) {
    return new DefaultNotifyService(applicationRepository, maintainerRepository, maintenanceRepository,
      notifyStrategyService, properties.getFallback().getStrategy(), properties.getFallback().getMaintainer());
  }

  @Bean
  public DefaultNotifier defaultNotifier(InstanceRepository instanceRepository, NotifyService notifyService) {
    return new DefaultNotifier(instanceRepository, notifyService);
  }

  @Bean
  public ApplicationApi applicationApi(ApplicationRepository repository) {
    return new ApplicationApi(repository);
  }

  @Bean
  public MaintainerApi maintainerApi(MaintainerRepository repository) {
    return new MaintainerApi(repository);
  }

  @Bean
  public MaintenanceApi maintenanceApi(MaintenanceRepository repository) {
    return new MaintenanceApi(repository);
  }

  @Bean
  public NotifyStrategyApi notifyStrategyApi(NotifyStrategyService notifyStrategyService) {
    return new NotifyStrategyApi(notifyStrategyService);
  }
}
