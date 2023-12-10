package cn.koala.security.log;

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
 * 登录日志接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/login-logs")
@Tag(name = "02-03 登录日志管理")
@SecurityRequirement(name = "spring-security")
public interface LoginLogApi {

  /**
   * 根据条件分页查询登录日志
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 登录日志列表
   */
  @PreAuthorize("hasAuthority('login-log.read')")
  @Operation(operationId = "listLoginLogs", summary = "根据条件分页查询登录日志")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "remoteAddress", description = "远程地址", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "userId", description = "用户id", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "username", description = "用户名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "isSuccessful", description = "是否成功", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "logTimeStart", description = "日志开始时间", schema = @Schema(type = "date-time"))
  @Parameter(in = ParameterIn.QUERY, name = "logTimeEnd", description = "日志结束时间", schema = @Schema(type = "date-time"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<LoginLog>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                    @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询字典
   *
   * @param id 字典id
   * @return 字典
   */
  @PreAuthorize("hasAuthority('login-log.read')")
  @Operation(operationId = "loadLoginLog", summary = "根据id查询登录日志")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "登录日志id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<LoginLog> load(@PathVariable("id") Long id);

  class DictionaryPageResult extends DataResponse<Page<LoginLog>> {

  }

  class DictionaryResult extends DataResponse<LoginLog> {

  }
}
