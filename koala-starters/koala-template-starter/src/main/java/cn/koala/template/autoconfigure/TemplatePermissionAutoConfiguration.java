package cn.koala.template.autoconfigure;

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
@ConditionalOnClass(name = "cn.koala.system.permission.PermissionRegister")
public class TemplatePermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "templatePermissionRegistrar")
  public PermissionRegistrar templatePermissionRegistrar() {
    CrudPermissionRegistrar result = new CrudPermissionRegistrar(
      "template",
      "模板管理",
      5000,
      null
    );
    result.addChild("render", "渲染");
    return result;
  }
}
