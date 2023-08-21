package cn.koala.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动属性类
 *
 * @author Houtaroy
 */
@Data
@ConfigurationProperties(prefix = "koala.boot")
public class BootProperties {
  private Map<String, Boolean> runners = new HashMap<>();
}
