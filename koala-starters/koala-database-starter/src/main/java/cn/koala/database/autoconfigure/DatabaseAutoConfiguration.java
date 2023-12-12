package cn.koala.database.autoconfigure;

import cn.koala.database.api.DatabaseApi;
import cn.koala.database.api.DatabaseApiImpl;
import cn.koala.database.repository.DatabaseRepository;
import cn.koala.database.service.DatabaseService;
import cn.koala.database.service.DefaultDatabaseService;
import cn.koala.validation.DefaultableMessageSourceLocator;
import cn.koala.validation.MessageSourceLocator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * 数据库自动配置类
 *
 * @author Houtaroy
 */
@MapperScan("cn.koala.database.repository")
@Configuration
public class DatabaseAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(AuditorAware.class)
  public DatabaseService databaseService(DatabaseRepository databaseRepository, AuditorAware<Long> auditorAware) {
    return new DefaultDatabaseService(databaseRepository, auditorAware);
  }

  @Bean
  @ConditionalOnMissingBean
  public DatabaseApi databaseApi(DatabaseService databaseService) {
    return new DatabaseApiImpl(databaseService);
  }

  @Bean
  @ConditionalOnMissingBean(name = "databaseMessageSourceLocator")
  public MessageSourceLocator databaseMessageSourceLocator() {
    return new DefaultableMessageSourceLocator("database");
  }
}
