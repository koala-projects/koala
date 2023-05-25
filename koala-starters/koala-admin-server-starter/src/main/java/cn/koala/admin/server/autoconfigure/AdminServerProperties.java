package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.Maintainer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Admin Server 配置属性类
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.admin.server")
@Data
public class AdminServerProperties {
  @NestedConfigurationProperty
  private Maintainer fallbackMaintainer;
}
