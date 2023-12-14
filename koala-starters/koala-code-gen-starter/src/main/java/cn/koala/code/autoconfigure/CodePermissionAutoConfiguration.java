package cn.koala.code.autoconfigure;

import cn.koala.system.permission.PermissionRegistrar;
import cn.koala.system.permission.SimplePermissionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 任务权限自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.koala.system.permission.PermissionRegister")
public class CodePermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "codeGenPermissionRegistrar")
  public PermissionRegistrar codeGenPermissionRegistrar() {
    return new SimplePermissionRegistrar(
      "code-gen",
      "代码生成",
      3000,
      null,
      Map.of("generate", "生成")
    );
  }
}
