package cn.koala.task.autoconfigure;

import cn.koala.system.PermissionRegistrar;
import cn.koala.system.support.CrudPermissionRegistrar;
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
@ConditionalOnClass(name = "cn.koala.system.PermissionRegistrar")
public class TaskPermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "taskPermissionRegistrar")
  public PermissionRegistrar taskPermissionRegistrar() {
    CrudPermissionRegistrar result =
      new CrudPermissionRegistrar("task", "任务管理", 7000, null);
    result.addChild("execute", "执行");
    return result;
  }
}
