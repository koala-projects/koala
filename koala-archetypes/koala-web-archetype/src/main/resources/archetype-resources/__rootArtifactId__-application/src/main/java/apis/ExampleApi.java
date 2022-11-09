#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.apis;

import ${package}.entities.ExampleEntity;
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
 * 示例表接口
 *
 * @author Eucalyptus Generator
 */
@RequestMapping("/examples")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "示例表管理")
public interface ExampleApi {

  /**
   * 根据条件分页查询示例表
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 示例表列表
   */
  @PreAuthorize("hasAuthority('examples:read')")
  @Operation(summary = "根据条件分页查询示例表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExamplePageResult.class))}
  )
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<ExampleEntity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                         @Parameter(hidden = true) Pageable pageable);

  /**
   * 查询示例表
   *
   * @param id 示例主键
   * @return 示例表
   */
  @PreAuthorize("hasAuthority('examples:read')")
  @Operation(summary = "根据id查询示例表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExampleResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "示例表id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<ExampleEntity> load(@PathVariable("id") String id);

  /**
   * 创建示例表
   *
   * @param entity 示例表数据实体
   * @return 已创建的示例表
   */
  @PreAuthorize("hasAuthority('examples:write')")
  @Operation(summary = "创建示例表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExampleResult.class))}
  )
  @PostMapping
  DataResponse<ExampleEntity> create(@RequestBody ExampleEntity entity);

  /**
   * 更新示例表
   *
   * @param id     示例主键
   * @param entity 示例表数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('examples:write')")
  @Operation(summary = "更新示例表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "示例表id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, ExampleEntity entity);

  /**
   * 删除示例表
   *
   * @param id 示例主键
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('examples:write')")
  @Operation(summary = "更新示例表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "示例表id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class ExamplePageResult extends DataResponse<Page<ExampleEntity>> {

  }

  class ExampleResult extends DataResponse<ExampleEntity> {

  }
}
