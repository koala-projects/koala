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
@RequestMapping("/api/departments")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "部门管理")
public interface DepartmentApi {

  /**
   * 根据条件分页查询部门
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 部门列表
   */
  @PreAuthorize("hasAuthority('department:read')")
  @Operation(summary = "根据条件分页查询权限")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "部门代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "部门名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "parentId", description = "父级部门id", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Department>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                      @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询部门
   *
   * @param id 部门id
   * @return 部门
   */
  @PreAuthorize("hasAuthority('department:read')")
  @Operation(summary = "根据id查询部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "部门id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Department> loadById(@PathVariable("id") String id);

  /**
   * 创建部门
   *
   * @param entity 部门数据实体
   * @return 部门
   */
  @PreAuthorize("hasAuthority('department:write')")
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
  @PreAuthorize("hasAuthority('department:write')")
  @Operation(summary = "更新部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "部门id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody DepartmentEntity entity);

  /**
   * 删除部门
   *
   * @param id 部门id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('department:write')")
  @Operation(summary = "删除部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "部门id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class DepartmentPageResult extends DataResponse<Page<DepartmentEntity>> {

  }

  class DepartmentResult extends DataResponse<DepartmentEntity> {

  }
}
