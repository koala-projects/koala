package cn.koala.sensitive.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 敏感词配置
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.sensitive-word")
@Data
public class SensitiveWordProperties {
  private String file;
  private boolean toolGood = true;
}
