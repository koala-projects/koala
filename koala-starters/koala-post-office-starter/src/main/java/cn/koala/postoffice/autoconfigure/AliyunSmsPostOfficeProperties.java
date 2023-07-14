package cn.koala.postoffice.autoconfigure;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云短信驿站属性类
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.post-office.aliyun-sms")
@Data
@NoArgsConstructor
public class AliyunSmsPostOfficeProperties {

  public static final String DEFAULT_ENDPOINT = "dysmsapi.aliyuncs.com";

  private String accessKeyId;

  private String accessKeySecret;

  private String endpoint = DEFAULT_ENDPOINT;
}
