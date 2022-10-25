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

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@RequestMapping("/api/users")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "用户接口")
public interface UserApi {

  /**
   * 根据条件分页查询用户
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 用户列表
   */
  @PreAuthorize("hasAuthority('user:read')")
  @Operation(summary = "根据条件分页查询用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "username", description = "用户名", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "nickname", description = "用户昵称", schema = @Schema(type = "string"))
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
  @PreAuthorize("hasAuthority('user:read')")
  @Operation(summary = "根据id查询用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<User> loadById(@PathVariable("id") String id);

  /**
   * 创建用户
   *
   * @param user 用户
   * @return 用户
   */
  @PreAuthorize("hasAuthority('user:write')")
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
  @PreAuthorize("hasAuthority('user:write')")
  @Operation(summary = "更新用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody UserEntity user);

  /**
   * 删除用户
   *
   * @param id 用户id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('user:write')")
  @Operation(summary = "删除用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  /**
   * 根据id查询用户角色id列表
   *
   * @param id 用户id
   * @return 角色id列表
   */
  @PreAuthorize("hasAuthority('user:read')")
  @Operation(summary = "根据id查询角色权限id列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRoleIdsResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @GetMapping("{id}/role-ids")
  DataResponse<List<String>> roleIds(@PathVariable("id") String id);

  /**
   * 设置用户角色id列表
   *
   * @param id      用户id
   * @param roleIds 角色id列表
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('user:write')")
  @Operation(summary = "角色授权")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @PutMapping("{id}/role-ids")
  Response setRoles(@PathVariable("id") String id, @RequestBody List<String> roleIds);

  class UserPageResult extends DataResponse<Page<User>> {

  }

  class UserResult extends DataResponse<User> {

  }

  class UserRoleIdsResult extends DataResponse<List<String>> {

  }
}
