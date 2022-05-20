package cn.koala.dingtalk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "ding-talk")
@Data
public class DingtalkProperties {
  private Long agentId;
  private String appKey;
  private String appSecret;
}
