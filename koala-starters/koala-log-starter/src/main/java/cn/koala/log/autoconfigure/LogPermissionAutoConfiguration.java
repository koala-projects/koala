package cn.koala.log.autoconfigure;

import cn.koala.system.PermissionRegistrar;
import cn.koala.system.support.SimplePermissionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 日志权限自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.koala.system.PermissionRegistrar")
public class LogPermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "logPermissionRegistrar")
  public PermissionRegistrar logPermissionRegistrar() {
    return new SimplePermissionRegistrar(
      "log",
      "日志管理",
      9000,
      1000L,
      Map.of("read", "读取")
    );
  }
}
