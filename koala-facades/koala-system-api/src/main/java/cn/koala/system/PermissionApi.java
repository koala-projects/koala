package cn.koala.system;

import cn.koala.swagger.PageableAsQueryParam;
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
 * @author Houtaroy
 */
@RequestMapping("/api/permissions")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "权限管理")
public interface PermissionApi {

  /**
   * 根据条件分页查询权限
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 权限列表
   */
  @PreAuthorize("hasAuthority('permission:read')")
  @Operation(summary = "根据条件分页查询权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "权限代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "权限名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "parentId", description = "父级权限id", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Permission>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                      @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询权限
   *
   * @param id 权限id
   * @return 权限
   */
  @PreAuthorize("hasAuthority('permission:read')")
  @Operation(summary = "根据id查询权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "权限id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Permission> loadById(@PathVariable("id") String id);

  /**
   * 创建权限
   *
   * @param permission 权限数据实体
   * @return 权限
   */
  @PreAuthorize("hasAuthority('permission:write')")
  @Operation(summary = "创建权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResult.class))}
  )
  @PostMapping
  DataResponse<Permission> create(@RequestBody PermissionEntity permission);

  /**
   * 更新权限
   *
   * @param id         权限id
   * @param permission 权限数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('permission:write')")
  @Operation(summary = "更新权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "权限id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody PermissionEntity permission);

  /**
   * 删除权限
   *
   * @param id 权限id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('permission:write')")
  @Operation(summary = "删除权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "权限id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class PermissionPageResult extends DataResponse<Page<PermissionEntity>> {

  }

  class PermissionResult extends DataResponse<PermissionEntity> {

  }
}
