package cn.koala.sensitive.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 代码生成器配置
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.sensitive-word")
@Data
public class SensitiveWordProperties {
  private String wordFile;
}
