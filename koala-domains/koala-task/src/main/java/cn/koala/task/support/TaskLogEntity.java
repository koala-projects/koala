package cn.koala.task.support;

import cn.koala.Koala;
import cn.koala.task.Task;
import cn.koala.task.TaskLog;
import cn.koala.toolkit.DateHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 任务日志数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "任务日志数据实体类")
public class TaskLogEntity implements TaskLog, Serializable {

  @Serial
  private static final long serialVersionUID = Koala.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "任务id")
  private Long taskId;

  private Execution execution;

  @Schema(description = "任务状态")
  private Status taskStatus;

  @Schema(description = "错误信息")
  private String taskError;

  @Schema(description = "开始时间")
  private Date startTime;

  @Schema(description = "结束时间")
  private Date endTime;

  public static TaskLogEntity from(Task task, Execution execution) {
    return TaskLogEntity.builder()
      .taskId(task.getId())
      .execution(execution)
      .startTime(DateHelper.now())
      .build();
  }
}
