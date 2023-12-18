package cn.koala.attachment.domain;

import cn.koala.attachment.util.AttachmentConstants;
import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

  @NotBlank(message = "原始文件名不允许为空", groups = {Create.class})
  @Size(max = 100, message = "原始文件名长度不能大于100", groups = {Create.class})
  @Schema(description = "原始文件名")
  private String originalFilename;

  @NotBlank(message = "文件类型不允许为空", groups = {Create.class})
  @Size(max = 100, message = "文件类型长度不能大于100", groups = {Create.class})
  @Schema(description = "文件类型")
  private String contentType;

  @NotBlank(message = "文件大小(KB)不允许为空", groups = {Create.class})
  @Size(max = 255, message = "文件大小(KB)长度不能大于", groups = {Create.class})
  @Schema(description = "文件大小(KB)")
  private Long size;

  @NotBlank(message = "存储路径不允许为空", groups = {Create.class})
  @Size(max = 255, message = "存储路径长度不能大于500", groups = {Create.class})
  @Schema(description = "存储路径")
  private String storagePath;
}
