package cn.koala.sensitiveword;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
@ConfigurationProperties("koala.sensitive-word")
@Data
public class SensitiveWordProperties {
  private List<String> resourceLocations = new ArrayList<>();
}
