package cn.koala.admin.server.autoconfigure;

import cn.koala.admin.server.Maintainer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Admin Server 配置属性类
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.admin.server")
@Data
public class AdminServerProperties {

  @NestedConfigurationProperty
  private MailProperties mail = new MailProperties();

  @NestedConfigurationProperty
  private FallbackProperties fallback = new FallbackProperties();

  @Data
  @NoArgsConstructor
  static class MailProperties {

    private static final String DEFAULT_SUBJECT = "运维通知";

    private String from;
    private String subject = DEFAULT_SUBJECT;
  }

  @Data
  @NoArgsConstructor
  static class FallbackProperties {

    private String strategy;

    @NestedConfigurationProperty
    private Maintainer maintainer;
  }
}
