package cn.koala.code;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 代码生成器配置
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.code-generator")
@Data
public class CodeGeneratorProperties {
  private String packageName = "cn.koala.code";
  private String tablePrefix = "t_";
  private String templatePath;
}
