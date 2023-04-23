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
  private String root;
}
