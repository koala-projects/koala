package cn.koala.system.autoconfigure;

import cn.koala.system.support.PermissionRegistrarConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限注册器属性类
 *
 * @author Houtaroy
 */
@Data
@ConfigurationProperties(prefix = "koala.system.permission.register")
public class PermissionRegisterProperties {

  private final Map<String, PermissionRegistrarConfig> registrars = new HashMap<>();
}
