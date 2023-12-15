package cn.koala.database.autoconfigure;

import cn.koala.database.api.DatabaseApi;
import cn.koala.database.api.DatabaseApiImpl;
import cn.koala.database.repository.DatabaseRepository;
import cn.koala.database.service.DatabaseService;
import cn.koala.database.service.DefaultDatabaseService;
import cn.koala.validation.DefaultableMessageSourceLocator;
import cn.koala.validation.MessageSourceLocator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan("cn.koala.database.repository")
public class DatabaseAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public DatabaseService databaseService(DatabaseRepository databaseRepository) {
    return new DefaultDatabaseService(databaseRepository);
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
