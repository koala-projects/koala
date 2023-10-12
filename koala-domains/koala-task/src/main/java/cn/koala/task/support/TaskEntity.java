package cn.koala.task.support;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.task.Task;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 任务数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "任务数据实体类")
public class TaskEntity extends AbstractEntity<Long> implements Task {

  @Schema(description = "主键")
  private Long id;

  @NotBlank(message = "任务名称不允许为空", groups = {Create.class})
  @Size(max = 100, message = "任务名称长度不能大于100", groups = {Create.class})
  @Schema(description = "任务名称")
  private String name;

  @Schema(description = "角色备注")
  @Size(max = 100, message = "角色备注长度不能大于500")
  private String remark;

  @NotBlank(message = "任务配置不允许为空", groups = {Create.class})
  @Size(max = 2000, message = "任务配置长度不能大于2000", groups = {Create.class})
  @Schema(description = "任务配置")
  private String taskConfig;

  @NotBlank(message = "触发配置不允许为空", groups = {Create.class})
  @Size(max = 2000, message = "触发配置长度不能大于2000", groups = {Create.class})
  @Schema(description = "触发配置")
  private String triggerConfig;
}
