package cn.koala.code.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 代码生成器配置
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.code-gen")
@Data
public class CodeGenProperties {

  private String path;

  private String packageName = "cn.koala.code";

  private String tablePrefix = "t_";

  private String tableRemarksSuffix = "表";
}
