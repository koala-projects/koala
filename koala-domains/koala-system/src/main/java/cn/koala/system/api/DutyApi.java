package cn.koala.system.api;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.system.domain.Duty;
import cn.koala.system.domain.DutyEntity;
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
 * 岗位接口
 *
 * @author Koala Code Gen
 */
@RestController
@RequestMapping("/api/duties")
@SecurityRequirement(name = "spring-security")
@Tag(name = "01-05 岗位管理")
public interface DutyApi {

  /**
   * 根据条件分页查询岗位
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 岗位分页结果
   */
  @PreAuthorize("hasAuthority('duty.read')")
  @Operation(operationId = "listDuties", summary = "根据条件分页查询岗位")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DutyPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "岗位代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "岗位名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "description", description = "岗位描述", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "isEnabled", description = "是否启用", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "isSystemic", description = "是否系统", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdBy", description = "创建人ID", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdTime", description = "创建时间", schema = @Schema(type = "date-time"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Duty>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询岗位
   *
   * @param id 岗位id
   * @return 岗位数据实体
   */
  @PreAuthorize("hasAuthority('duty.read')")
  @Operation(operationId = "loadDuty", summary = "根据id查询岗位")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DutyResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "岗位id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Duty> load(@PathVariable("id") Long id);

  /**
   * 创建岗位
   *
   * @param entity 岗位数据实体
   * @return 岗位数据实体
   */
  @PreAuthorize("hasAuthority('duty.create')")
  @Operation(operationId = "createDuty", summary = "创建岗位")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DutyResult.class))}
  )
  @PostMapping
  DataResponse<Duty> create(@Validated(Create.class) @RequestBody DutyEntity entity);

  /**
   * 更新岗位
   *
   * @param id     岗位id
   * @param entity 岗位数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('duty.update')")
  @Operation(operationId = "updateDuty", summary = "更新岗位")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "岗位id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @Validated(Update.class) @RequestBody DutyEntity entity);

  /**
   * 删除岗位
   *
   * @param id 岗位id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('duty.delete')")
  @Operation(operationId = "deleteDuty", summary = "删除岗位")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "岗位id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  class DutyPageResult extends DataResponse<Page<DutyEntity>> {

  }

  class DutyResult extends DataResponse<DutyEntity> {

  }
}
