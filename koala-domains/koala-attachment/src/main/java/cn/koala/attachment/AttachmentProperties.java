package cn.koala.attachment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 附件参数
 *
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.attachment")
@Data
public class AttachmentProperties {

  /**
   * 存储模式, 当前支持本地local和对象存储minio
   */
  private String type = "local";

  /**
   * 根目录, 仅在本地存储时生效
   */
  private String root;
}
