package cn.koala.task;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.persist.validator.EditableId;
import cn.koala.task.support.TaskEntity;
import cn.koala.validation.group.Create;
import cn.koala.validation.group.Update;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 任务接口
 *
 * @author Koala Code Generator
 */
@RestController
@RequestMapping("/api/tasks")
@Tag(name = "07-01 任务管理")
@SecurityRequirement(name = "spring-security")
public interface TaskApi {

  /**
   * 根据条件分页查询任务
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 任务分页结果
   */
  @PreAuthorize("hasAuthority('task.read')")
  @Operation(operationId = "listTasks", summary = "根据条件分页查询任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TaskPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "任务名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "isEnabled", description = "是否启用", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdBy", description = "创建人ID", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdTime", description = "创建时间", schema = @Schema(type = "date-time"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Task>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询任务
   *
   * @param id 任务id
   * @return 任务数据实体
   */
  @PreAuthorize("hasAuthority('task.read')")
  @Operation(operationId = "loadTask", summary = "根据id查询任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TaskResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "任务id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Task> load(@PathVariable("id") Long id);

  /**
   * 创建任务
   *
   * @param entity 任务数据实体
   * @return 任务数据实体
   */
  @PreAuthorize("hasAuthority('task.create')")
  @Operation(operationId = "createTask", summary = "创建任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TaskResult.class))}
  )
  @PostMapping
  DataResponse<TaskEntity> create(@Validated(Create.class) @RequestBody TaskEntity entity);

  /**
   * 更新任务
   *
   * @param id     任务id
   * @param entity 任务数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('task.update')")
  @Operation(operationId = "updateTask", summary = "更新任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "任务id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@EditableId(TaskEntity.class) @PathVariable("id") Long id,
                  @Validated(Update.class) @RequestBody TaskEntity entity);

  /**
   * 删除任务
   *
   * @param id 任务id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('task.delete')")
  @Operation(operationId = "deleteTask", summary = "删除任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "任务id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@EditableId(TaskEntity.class) @PathVariable("id") Long id);

  @PreAuthorize("hasAuthority('task.update')")
  @Operation(operationId = "enableTask", summary = "启用任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "任务id", schema = @Schema(type = "integer"))
  @PutMapping("{id}/enable")
  Response enable(@PathVariable("id") Long id);

  @PreAuthorize("hasAuthority('task.update')")
  @Operation(operationId = "disableTask", summary = "禁用任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "任务id", schema = @Schema(type = "integer"))
  @PutMapping("{id}/disable")
  Response disable(@PathVariable("id") Long id);

  @PreAuthorize("hasAuthority('task.execute')")
  @Operation(operationId = "executeTask", summary = "执行任务")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "任务id", schema = @Schema(type = "integer"))
  @PostMapping("{id}/execute")
  Response execute(@PathVariable("id") Long id);

  class TaskPageResult extends DataResponse<Page<TaskEntity>> {

  }

  class TaskResult extends DataResponse<TaskEntity> {

  }
}
