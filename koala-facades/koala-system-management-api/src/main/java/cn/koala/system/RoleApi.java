package cn.koala.system;

import cn.koala.swagger.PageableAsQueryParam;
import cn.koala.system.mybatis.RoleEntity;
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
@RequestMapping("/roles")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "role", description = "角色接口")
public interface RoleApi {

  /**
   * 根据条件分页查询角色
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 角色列表
   */
  @PreAuthorize("hasAuthority('api:roles:page')")
  @Operation(summary = "根据条件分页查询角色", tags = {"role"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RolePageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "角色名称", description = "角色名称", schema = @Schema(type = "string"))
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
  @PreAuthorize("hasAuthority('api:roles:loadById')")
  @Operation(summary = "根据id查询角色", tags = {"role"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Role> loadById(@PathVariable("id") String id);

  /**
   * 创建角色
   *
   * @param role 角色数据实体
   * @return 角色
   */
  @Operation(summary = "创建角色", tags = {"role"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResult.class))}
  )
  @PostMapping
  DataResponse<Role> create(@RequestBody RoleEntity role);

  /**
   * 更新角色
   *
   * @param id   角色id
   * @param role 角色数据实体
   * @return 操作结果
   */
  @Operation(summary = "更新角色", tags = {"role"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody RoleEntity role);

  /**
   * 删除角色
   *
   * @param id 角色id
   * @return 操作结果
   */
  @Operation(summary = "删除角色", tags = {"role"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "角色id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class RolePageResult extends DataResponse<Page<Role>> {

  }

  class RoleResult extends DataResponse<Role> {

  }
}
