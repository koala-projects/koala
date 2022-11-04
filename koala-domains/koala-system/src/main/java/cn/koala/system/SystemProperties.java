package cn.koala.system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Houtaroy
 */
@ConfigurationProperties("koala.system")
@Data
public class SystemProperties {
  private String defaultPassword = "koala-projects";
  private boolean changePassword = true;
}
