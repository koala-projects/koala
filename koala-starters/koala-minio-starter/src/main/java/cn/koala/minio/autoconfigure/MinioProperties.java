package cn.koala.minio.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Minio配置类
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.minio")
@Data
public class MinioProperties {
  private String endpoint;
  private String accessKey;
  private String secretKey;
  private String region;
}
