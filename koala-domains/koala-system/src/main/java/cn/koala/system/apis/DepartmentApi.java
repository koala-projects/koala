package cn.koala.system.apis;

import cn.koala.system.Department;
import cn.koala.system.entities.DepartmentEntity;
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
 * @author Houtaroy
 */
@RequestMapping("/api/departments")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "部门管理")
public interface DepartmentApi {

  /**
   * 查询部门树
   *
   * @return 树形结构部门列表
   */
  @PreAuthorize("hasAuthority('system:department:tree')")
  @Operation(summary = "查询部门树")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentTreeResult.class))}
  )
  @GetMapping("tree")
  DataResponse<List<TreeNode>> tree();

  /**
   * 根据id查询部门
   *
   * @param id 部门id
   * @return 部门
   */
  @PreAuthorize("hasAuthority('system:department:load')")
  @Operation(summary = "根据id查询部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "部门id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Department> load(@PathVariable("id") Long id);

  /**
   * 创建部门
   *
   * @param entity 部门数据实体
   * @return 部门
   */
  @PreAuthorize("hasAuthority('system:department:create')")
  @Operation(summary = "创建部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentResult.class))}
  )
  @PostMapping
  DataResponse<Department> create(@RequestBody DepartmentEntity entity);

  /**
   * 更新部门
   *
   * @param id     部门id
   * @param entity 部门数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:department:update')")
  @Operation(summary = "更新部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "部门id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody DepartmentEntity entity);

  /**
   * 删除部门
   *
   * @param id 部门id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:department:delete')")
  @Operation(summary = "删除部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "部门id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  class DepartmentResult extends DataResponse<DepartmentEntity> {

  }

  class DepartmentTreeResult extends DataResponse<List<TreeNode>> {

  }
}
