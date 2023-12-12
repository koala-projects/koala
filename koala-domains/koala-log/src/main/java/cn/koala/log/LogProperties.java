package cn.koala.log;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志参数
 *
 * @author Houtaroy
 */
@Data
@ConfigurationProperties(prefix = "koala.log")
public class LogProperties {
  private boolean enabled = true;
  private List<String> ignoredPatterns = new ArrayList<>();
}
