package cn.koala.security.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置
 *
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.security")
@Data
public class SecurityProperties {
  private final List<String> permitAllPatterns = new ArrayList<>();
}
