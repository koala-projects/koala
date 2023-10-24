package cn.koala.system;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.persist.validator.EditableId;
import cn.koala.system.apis.request.RoleAuthorizeRequest;
import cn.koala.system.entities.RoleEntity;
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

import java.util.List;
import java.util.Map;

/**
 * 角色接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/roles")
@RestController
@Validated
@SecurityRequirement(name = "spring-security")
@Tag(name = "01-02 角色管理")
public interface RoleApi {

  /**
   * 根据条件分页查询角色
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 角色列表
   */
  @PreAuthorize("hasAuthority('role.read')")
  @Operation(operationId = "listRoles", summary = "根据条件分页查询角色")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RolePageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "codeLike", description = "角色代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "nameLike", description = "角色名称", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Role>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询角色
   *
   * @param id 角色id
   * @return 角色
   */
  @PreAuthorize("hasAuthority('role.read')")
  @Operation(operationId = "loadRole", summary = "根据id查询角色")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Role> load(@PathVariable("id") Long id);

  /**
   * 创建角色
   *
   * @param entity 角色数据实体
   * @return 角色
   */
  @PreAuthorize("hasAuthority('role.create')")
  @Operation(operationId = "createRole", summary = "创建角色")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResult.class))}
  )
  @PostMapping
  DataResponse<Role> create(@Validated(Create.class) @RequestBody RoleEntity entity);

  /**
   * 更新角色
   *
   * @param id     角色id
   * @param entity 角色数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('role.update')")
  @Operation(operationId = "updateRole", summary = "更新角色")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@EditableId(Role.class) @PathVariable("id") Long id,
                  @Validated(Update.class) @RequestBody RoleEntity entity);

  /**
   * 删除角色
   *
   * @param id 角色id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('role.delete')")
  @Operation(operationId = "deleteRole", summary = "删除角色")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@EditableId(Role.class) @PathVariable("id") Long id);

  /**
   * 根据id查询全选权限id列表
   *
   * @param id 角色id
   * @return 全选权限id列表
   */
  @PreAuthorize("hasAuthority('role.update')")
  @Operation(operationId = "listRoleCheckedPermissionIds", summary = "根据id查询角色权限关系列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = CheckedPermissionIdsResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "integer"))
  @GetMapping("{id}/checked-permission-ids")
  DataResponse<List<Long>> getCheckedPermissionIds(@PathVariable("id") Long id);

  /**
   * 角色授权
   *
   * @param id      角色id
   * @param request 角色授权请求
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('role.update')")
  @Operation(operationId = "setRolePermissions", summary = "角色授权")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "integer"))
  @PutMapping("{id}/authorize")
  Response setRolePermissions(@PathVariable("id") Long id, @RequestBody RoleAuthorizeRequest request);

  /**
   * 根据id查询角色的用户列表
   *
   * @param id 角色id
   * @return 用户列表
   */
  @PreAuthorize("hasAuthority('role.read')")
  @Operation(operationId = "listRoleUser", summary = "根据id查询角色的用户列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = RoleUserListResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "integer"))
  @GetMapping("{id}/users")
  DataResponse<List<User>> listUser(@PathVariable("id") Long id);

  class RolePageResult extends DataResponse<Page<RoleEntity>> {

  }

  class RoleResult extends DataResponse<RoleEntity> {

  }

  class CheckedPermissionIdsResult extends DataResponse<List<Long>> {

  }

  class RoleUserListResult extends DataResponse<List<User>> {

  }
}
