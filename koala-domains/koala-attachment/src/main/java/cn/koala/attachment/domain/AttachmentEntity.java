package cn.koala.attachment.domain;

import cn.koala.attachment.util.AttachmentConstants;
import cn.koala.mybatis.domain.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 附件实体类
 *
 * @author Koala Code Generator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "附件实体类")
public class AttachmentEntity extends AbstractEntity<Long, Long> implements Attachment, Serializable {

  @Serial
  private static final long serialVersionUID = AttachmentConstants.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "原始文件名")
  private String originalFilename;

  @Schema(description = "文件类型")
  private String contentType;

  @Schema(description = "文件大小(KB)")
  private Long size;

  @Schema(description = "存储路径")
  private String storagePath;

  @Schema(description = "存储资源标识")
  private String storageUri;
}
