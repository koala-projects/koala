package cn.koala.log.apis;

import cn.koala.log.Log;
import cn.koala.log.LogEntity;
import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.web.DataResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/logs")
@Tag(name = "日志管理")
@SecurityRequirement(name = "spring-security")
public interface LogApi {

  /**
   * 根据条件分页查询日志
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 日志列表
   */
  @PreAuthorize("hasAuthority('log.read')")
  @Operation(operationId = "listLogs", summary = "根据条件分页查询日志")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "module", description = "模块名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "isSucceeded", description = "是否成功", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "logTimeStart", description = "日志开始时间", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "logTimeEnd", description = "日志结束时间", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Log>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                               @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询日志
   *
   * @param id 模板id
   * @return 模板
   */
  @PreAuthorize("hasAuthority('log.read')")
  @Operation(operationId = "loadLog", summary = "根据id查询日志")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "日志id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Log> load(@PathVariable("id") Long id);


  class LogPageResult extends DataResponse<Page<LogEntity>> {

  }

  class LogResult extends DataResponse<LogEntity> {

  }
}
