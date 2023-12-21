package cn.koala.authorization.autoconfigure;

import cn.koala.system.permission.PermissionRegistrar;
import cn.koala.system.permission.SimplePermissionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 登录日志权限注册器
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.koala.system.permission.PermissionRegister")
public class LoginLogPermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "loginLogPermissionRegistrar")
  public PermissionRegistrar attachmentPermissionRegistrar() {
    return new SimplePermissionRegistrar(
      "login-log",
      "登录日志管理",
      2300,
      null,
      Map.of("read", "读取")
    );
  }
}
