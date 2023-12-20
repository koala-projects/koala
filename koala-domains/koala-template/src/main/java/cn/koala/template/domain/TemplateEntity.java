package cn.koala.template.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.template.util.TemplateConstants;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模板实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "模板实体")
public class TemplateEntity extends AbstractEntity<Long, Long> implements Template, Serializable {

  @Serial
  private static final long serialVersionUID = TemplateConstants.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "模板组id")
  @NotNull(message = "模板组id不能为空", groups = Create.class)
  private Long groupId;

  @Schema(description = "模板名称")
  @NotBlank(message = "模板名称不能为空", groups = Create.class)
  @Size(min = 2, max = 20, message = "模板名称长度必须在2-20之间", groups = Create.class)
  private String name;

  @Schema(description = "模板描述")
  @Size(max = 200, message = "模板描述长度不能超过200", groups = Create.class)
  private String description;

  @Schema(description = "模板内容")
  @NotBlank(message = "模板内容不能为空", groups = Create.class)
  private String content;
}
