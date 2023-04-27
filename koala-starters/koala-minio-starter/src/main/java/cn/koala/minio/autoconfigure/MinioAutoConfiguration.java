package cn.koala.minio.autoconfigure;

import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Minio自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "koala.minio", name = {"endpoint", "access-key", "secret-key"})
  public MinioClient minioClient(MinioProperties properties) {
    MinioClient.Builder builder = MinioClient.builder()
      .endpoint(properties.getEndpoint())
      .credentials(properties.getAccessKey(), properties.getSecretKey());
    if (StringUtils.hasText(properties.getRegion())) {
      builder.region(properties.getRegion());
    }
    return builder.build();
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "koala.minio", name = {"endpoint", "access-key", "secret-key"})
  public MinioAsyncClient minioAsyncClient(MinioProperties properties) {
    MinioAsyncClient.Builder builder = MinioAsyncClient.builder()
      .endpoint(properties.getEndpoint())
      .credentials(properties.getAccessKey(), properties.getSecretKey());
    if (StringUtils.hasText(properties.getRegion())) {
      builder.region(properties.getRegion());
    }
    return builder.build();
  }
}
