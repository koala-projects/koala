package cn.koala.oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Houtaroy
 */
@ConfigurationProperties("koala.resource-server")
@Data
public class ResourceServerProperties {
  private List<String> permitAllAntPatterns;
}
