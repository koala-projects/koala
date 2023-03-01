package cn.koala.log;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置
 *
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.log")
@Data
public class LogProperties {
  private boolean enabled = true;
  private List<String> ignoredPatterns = new ArrayList<>();
}
