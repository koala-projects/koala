package cn.koala.system.apis;

import cn.koala.system.Permission;
import cn.koala.system.entities.PermissionEntity;
import cn.koala.toolkit.tree.TreeNode;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/permissions")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "权限管理")
public interface PermissionApi {

  /**
   * 查询权限树
   *
   * @return 权限树
   */
  @PreAuthorize("hasAuthority('system:permission:tree')")
  @Operation(summary = "查询权限树")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionTreeResult.class))}
  )
  @GetMapping("tree")
  DataResponse<List<TreeNode>> tree();

  /**
   * 根据id查询权限
   *
   * @param id 权限id
   * @return 权限
   */
  @PreAuthorize("hasAuthority('system:permission:load')")
  @Operation(summary = "根据id查询权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "权限id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Permission> load(@PathVariable("id") Long id);

  /**
   * 创建权限
   *
   * @param permission 权限数据实体
   * @return 权限
   */
  @PreAuthorize("hasAuthority('system:permission:create')")
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
  @PreAuthorize("hasAuthority('system:permission:update')")
  @Operation(summary = "更新权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "权限id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody PermissionEntity permission);

  /**
   * 删除权限
   *
   * @param id 权限id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:permission:delete')")
  @Operation(summary = "删除权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "权限id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  class PermissionTreeResult extends DataResponse<List<TreeNode>> {

  }

  class PermissionResult extends DataResponse<PermissionEntity> {

  }
}
