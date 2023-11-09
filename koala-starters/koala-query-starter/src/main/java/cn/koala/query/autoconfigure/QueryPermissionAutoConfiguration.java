package cn.koala.query.autoconfigure;

import cn.koala.system.permission.CrudPermissionRegistrar;
import cn.koala.system.permission.PermissionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 任务权限自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.koala.system.permission.PermissionRegistrar")
public class QueryPermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "queryPermissionRegistrar")
  public PermissionRegistrar queryPermissionRegistrar() {
    CrudPermissionRegistrar result =
      new CrudPermissionRegistrar("query", "查询管理", 8000, null);
    result.addChild("execute", "执行");
    return result;
  }
}
