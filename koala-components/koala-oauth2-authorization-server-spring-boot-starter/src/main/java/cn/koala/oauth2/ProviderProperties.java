package cn.koala.oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.security")
@Data
public class ProviderProperties {
  private final String issuer = "http://127.0.0.1:9999";
}
