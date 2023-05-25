package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.api.ApplicationApi;
import cn.koala.admin.server.api.MaintainerApi;
import cn.koala.admin.server.api.MaintenanceApi;
import cn.koala.admin.server.repository.ApplicationRepository;
import cn.koala.admin.server.repository.MaintainerRepository;
import cn.koala.admin.server.repository.MaintenanceRepository;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Admin Server自动配置
 * <p>
 * 自动装配管理接口
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(AdminServerProperties.class)
@EnableAdminServer
@EnableMongoRepositories(basePackages = "cn.koala.admin.server.repository")
public class AdminServerAutoConfiguration {

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
}
