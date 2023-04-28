package cn.koala.attachment;

import cn.koala.attachment.Attachment;
import cn.koala.validation.group.Add;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 附件数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "附件数据实体类")
public class AttachmentEntity implements Attachment {

  @Schema(description = "主键")
  private Long id;

  @NotBlank(message = "原始文件名不允许为空", groups = {Add.class})
  @Size(max = 100, message = "原始文件名长度不能大于100", groups = {Add.class})
  @Schema(description = "原始文件名")
  private String originalFilename;

  @NotBlank(message = "文件类型不允许为空", groups = {Add.class})
  @Size(max = 100, message = "文件类型长度不能大于100", groups = {Add.class})
  @Schema(description = "文件类型")
  private String contentType;

  @NotBlank(message = "文件大小(KB)不允许为空", groups = {Add.class})
  @Size(max = 255, message = "文件大小(KB)长度不能大于", groups = {Add.class})
  @Schema(description = "文件大小(KB)")
  private Long size;

  @NotBlank(message = "存储路径不允许为空", groups = {Add.class})
  @Size(max = 255, message = "存储路径长度不能大于500", groups = {Add.class})
  @Schema(description = "存储路径")
  private String storagePath;

  @Schema(description = "创建人ID")
  private Long createdBy;

  @Schema(description = "创建时间")
  private Date createdTime;

  @Schema(description = "最后更新人ID")
  private Long lastModifiedBy;

  @Schema(description = "最后更新时间")
  private Date lastModifiedTime;

  @Schema(description = "删除人ID")
  private Long deletedBy;

  @Schema(description = "删除时间")
  private Date deletedTime;
}
