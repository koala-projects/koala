package cn.koala.system.apis;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.system.User;
import cn.koala.system.entities.UserEntity;
import cn.koala.web.DataRequest;
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

import java.util.List;
import java.util.Map;

/**
 * 用户管理接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/users")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "用户管理")
public interface UserApi {

  /**
   * 根据条件分页查询用户
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 用户列表
   */
  @PreAuthorize("hasAuthority('system:user:page')")
  @Operation(summary = "根据条件分页查询用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "username", description = "用户名", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "nickname", description = "用户昵称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "departmentId", description = "部门ID", schema = @Schema(type = "integer"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<User>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询用户
   *
   * @param id 用户id
   * @return 用户
   */
  @PreAuthorize("hasAuthority('system:user:load')")
  @Operation(summary = "根据id查询用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<User> load(@PathVariable("id") Long id);

  /**
   * 创建用户
   *
   * @param user 用户
   * @return 用户
   */
  @PreAuthorize("hasAuthority('system:user:create')")
  @Operation(summary = "创建用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResult.class))}
  )
  @PostMapping
  DataResponse<User> create(@RequestBody UserEntity user);

  /**
   * 更新用户
   *
   * @param id   用户id
   * @param user 用户
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:user:update')")
  @Operation(summary = "更新用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody UserEntity user);

  /**
   * 删除用户
   *
   * @param id 用户id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:user:delete')")
  @Operation(summary = "删除用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  /**
   * 根据id查询用户角色id列表
   *
   * @param id 用户id
   * @return 角色id列表
   */
  @PreAuthorize("hasAuthority('system:user:update')")
  @Operation(summary = "根据id查询用户角色id列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = RoleIdsResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "integer"))
  @GetMapping("{id}/role-ids")
  DataResponse<List<Long>> getRoleIds(@PathVariable("id") Long id);

  /**
   * 设置用户角色id列表
   *
   * @param id      用户id
   * @param request 角色id列表
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:user:update')")
  @Operation(summary = "用户授权")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "integer"))
  @PutMapping("{id}/role-ids")
  Response setRoleIds(@PathVariable("id") Long id, @RequestBody DataRequest<List<Long>> request);

  /**
   * 根据id查询用户部门id列表
   *
   * @param id 用户id
   * @return 部门id列表
   */
  @PreAuthorize("hasAuthority('system:user:update')")
  @Operation(summary = "根据id查询用户部门id列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentIdsResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "integer"))
  @GetMapping("{id}/department-ids")
  DataResponse<List<Long>> getDepartmentIds(@PathVariable("id") Long id);

  /**
   * 设置用户部门ID列表
   *
   * @param id      用户id
   * @param request 部门ID列表
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:user:update')")
  @Operation(summary = "设置用户部门ID列表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "部门id", schema = @Schema(type = "integer"))
  @PutMapping("{id}/department-ids")
  Response setDepartmentIds(@PathVariable("id") Long id, @RequestBody DataRequest<List<Long>> request);


  class UserPageResult extends DataResponse<Page<User>> {

  }

  class UserResult extends DataResponse<User> {

  }

  class RoleIdsResult extends DataResponse<List<String>> {

  }

  class DepartmentIdsResult extends DataResponse<List<String>> {

  }
}
