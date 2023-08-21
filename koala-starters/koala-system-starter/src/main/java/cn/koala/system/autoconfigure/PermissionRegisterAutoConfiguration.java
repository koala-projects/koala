package cn.koala.system.autoconfigure;

import cn.koala.system.PermissionRegistrar;
import cn.koala.system.repositories.PermissionRepository;
import cn.koala.system.support.PermissionRegister;
import cn.koala.system.support.SystemPermissionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 权限注册器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(PermissionRegisterProperties.class)
public class PermissionRegisterAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "systemPermissionRegistrar")
  public PermissionRegistrar systemPermissionRegistrar() {
    return new SystemPermissionRegistrar();
  }

  @Bean
  public PermissionRegister permissionRegister(
    List<PermissionRegistrar> registrars, PermissionRegisterProperties properties, PermissionRepository repository) {

    return new PermissionRegister(registrars, repository, properties.getRegistrars());
  }
}
