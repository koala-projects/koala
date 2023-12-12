package cn.koala.database.autoconfigure;

import cn.koala.system.permission.CrudPermissionRegistrar;
import cn.koala.system.permission.PermissionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库管理权限自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.koala.system.permission.PermissionRegistrar")
public class DatabasePermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "databasePermissionRegistrar")
  public PermissionRegistrar databasePermissionRegistrar() {
    return new CrudPermissionRegistrar("database", "数据库管理", 4000, null);
  }
}
