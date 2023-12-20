package cn.koala.template.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.template.util.TemplateConstants;
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
 * 模板组实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "模板组实体")
public class TemplateGroupEntity extends AbstractEntity<Long, Long> implements TemplateGroup, Serializable {

  @Serial
  private static final long serialVersionUID = TemplateConstants.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "模板组名称")
  @NotBlank(message = "模板组名称不能为空", groups = Create.class)
  @Size(min = 2, max = 20, message = "模板组名称长度必须在2-20之间", groups = Create.class)
  private String name;

  @Schema(description = "模板组备注")
  @Size(max = 200, message = "模板组备注长度不能超过200", groups = Create.class)
  private String description;
}
