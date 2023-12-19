package cn.koala.task.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.task.util.TaskConstants;
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
 * 任务实体类
 *
 * @author Koala Code Generator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "任务实体类")
public class TaskEntity extends AbstractEntity<Long, Long> implements Task, Serializable {

  @Serial
  private static final long serialVersionUID = TaskConstants.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  private Long id;

  @NotBlank(message = "任务名称不允许为空", groups = {Create.class})
  @Size(max = 20, message = "任务名称长度不能大于100", groups = {Create.class})
  @Schema(description = "任务名称")
  private String name;

  @Schema(description = "角色描述")
  @Size(max = 200, message = "角色备注长度不能大于200")
  private String description;

  @NotBlank(message = "任务配置不允许为空", groups = {Create.class})
  @Schema(description = "任务配置")
  private String taskConfig;

  @NotBlank(message = "触发配置不允许为空", groups = {Create.class})
  @Schema(description = "触发配置")
  private String triggerConfig;
}
