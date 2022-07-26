package cn.koala.system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统管理配置
 *
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.system-management")
@Data
public class SystemManagementProperties {
  private String defaultUserPassword = "koala";
}
