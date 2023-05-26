package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.NotifyService;
import cn.koala.admin.server.repository.ApplicationRepository;
import cn.koala.admin.server.repository.MaintainerRepository;
import cn.koala.admin.server.repository.MaintenanceRepository;
import cn.koala.admin.server.strategy.NotifyStrategy;
import cn.koala.admin.server.strategy.support.MailNotifyStrategy;
import cn.koala.admin.server.support.DefaultNotifier;
import cn.koala.admin.server.support.DefaultNotifyService;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.MailNotifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.util.List;

/**
 * Admin Server 通知自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@AutoConfigureAfter(MailSenderAutoConfiguration.class)
public class AdminServerNotifyAutoConfiguration {

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
  public NotifyService notifyService(ApplicationRepository applicationRepository,
                                     MaintainerRepository maintainerRepository,
                                     MaintenanceRepository maintenanceRepository,
                                     List<NotifyStrategy> strategies, AdminServerProperties properties) {
    return new DefaultNotifyService(applicationRepository, maintainerRepository, maintenanceRepository, strategies,
      properties.getFallback().getStrategy(), properties.getFallback().getMaintainer());
  }

  @Bean
  public DefaultNotifier defaultNotifier(InstanceRepository instanceRepository, NotifyService notifyService) {
    return new DefaultNotifier(instanceRepository, notifyService);
  }
}
