package cn.koala.system.autoconfigure;

import cn.koala.system.support.PermissionRegistrarConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置属性类
 *
 * @author Houtaroy
 */
@Data
@ConfigurationProperties(prefix = "koala.system")
public class SystemProperties {

  private boolean adminRegister;

  private Map<String, PermissionRegistrarConfig> permissionRegistrars = new HashMap<>();
}
