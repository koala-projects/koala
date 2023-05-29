package cn.koala.database.autoconfigure;

import cn.koala.database.DatabaseInitializer;
import cn.koala.database.apis.DatabaseApi;
import cn.koala.database.apis.DatabaseApiImpl;
import cn.koala.database.repositories.DatabaseRepository;
import cn.koala.database.services.DatabaseService;
import cn.koala.database.services.DatabaseServiceImpl;
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
@MapperScan("cn.koala.database.repositories")
@Configuration
public class DatabaseAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public DatabaseService databaseService(DatabaseRepository databaseRepository) {
    return new DatabaseServiceImpl(databaseRepository);
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

  @Bean
  public DatabaseInitializer databaseInitializer() {
    return new DatabaseInitializer();
  }
}
