package cn.koala.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.security")
@Getter
public class ProviderProperties {
  private final String issuer = "http://127.0.0.1:9999";
}
