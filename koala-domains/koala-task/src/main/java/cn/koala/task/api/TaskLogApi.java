package cn.koala.task.api;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.task.domain.TaskLog;
import cn.koala.task.domain.TaskLogEntity;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 任务日志接口
 *
 * @author Koala Code Generator
 */
@RestController
@RequestMapping("/api/task-logs")
@Tag(name = "07-02 任务日志管理")
public interface TaskLogApi {

  /**
   * 根据条件分页查询任务日志
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 任务日志分页结果
   */
  @PreAuthorize("hasAuthority('task-log.read')")
  @Operation(operationId = "listTaskLogs", summary = "根据条件分页查询任务日志")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TaskLogPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "taskId", description = "任务id", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "taskMode", description = "执行类型", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "taskStatus", description = "任务状态", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "startTime", description = "开始时间", schema = @Schema(type = "date-time"))
  @Parameter(in = ParameterIn.QUERY, name = "endTime", description = "结束时间", schema = @Schema(type = "date-time"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<TaskLog>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                   @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询任务日志
   *
   * @param id 任务日志id
   * @return 任务日志数据实体
   */
  @PreAuthorize("hasAuthority('task-log.read')")
  @Operation(operationId = "loadTaskLog", summary = "根据id查询任务日志")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TaskLogResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "任务日志id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<TaskLog> load(@PathVariable("id") Long id);

  class TaskLogPageResult extends DataResponse<Page<TaskLogEntity>> {

  }

  class TaskLogResult extends DataResponse<TaskLogEntity> {

  }
}
