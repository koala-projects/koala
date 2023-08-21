package cn.koala.attachment.autoconfigure;

import cn.koala.system.PermissionRegistrar;
import cn.koala.system.support.CrudPermissionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 附件权限自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.koala.system.PermissionRegistrar")
public class AttachmentPermissionAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "attachmentPermissionRegistrar")
  public PermissionRegistrar attachmentPermissionRegistrar() {
    return new CrudPermissionRegistrar("attachment", "附件管理", 600);
  }
}
