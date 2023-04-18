package cn.koala.security.autoconfigure;

import lombok.Data;
import lombok.NoArgsConstructor;
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

  private List<String> permitAllPatterns = new ArrayList<>();

  private GrantType grantType = new GrantType();

  @Data
  @NoArgsConstructor
  public static class GrantType {
    private boolean password = false;
  }
}
