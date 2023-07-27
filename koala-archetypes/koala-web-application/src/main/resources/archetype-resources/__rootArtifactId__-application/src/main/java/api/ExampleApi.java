#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import ${package}.entity.ExampleEntity;
import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.persist.validator.EditableId;
import cn.koala.validation.group.Create;
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
 * 示例表接口
 *
 * @author koala web application
 */
@RequestMapping("/api/examples")
@RestController
@Validated
@SecurityRequirement(name = "spring-security")
@Tag(name = "示例管理")
public interface ExampleApi {

  /**
   * 根据条件分页查询示例
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 示例实体列表
   */
  @PreAuthorize("hasAuthority('example:page')")
  @Operation(operationId = "listExamples", summary = "根据条件分页查询示例")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExamplePageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "示例名称", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<ExampleEntity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                         @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询示例
   *
   * @param id 示例id
   * @return 示例实体
   */
  @PreAuthorize("hasAuthority('example:load')")
  @Operation(operationId = "loadExample", summary = "根据id查询示例")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExampleResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "示例id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<ExampleEntity> load(@PathVariable("id") Long id);

  /**
   * 创建示例
   *
   * @param entity 示例实体
   * @return 示例实体
   */
  @PreAuthorize("hasAuthority('example:create')")
  @Operation(operationId = "createExample", summary = "创建示例")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExampleResult.class))}
  )
  @PostMapping
  DataResponse<ExampleEntity> create(@Validated(Create.class) @RequestBody ExampleEntity entity);

  /**
   * 更新示例
   *
   * @param id     示例id
   * @param entity 示例实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('example:update')")
  @Operation(operationId = "updateExample", summary = "更新示例")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "示例id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@EditableId(ExampleEntity.class) @PathVariable("id") Long id, @RequestBody ExampleEntity entity);

  /**
   * 删除示例
   *
   * @param id 示例id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('example:delete')")
  @Operation(operationId = "deleteExample", summary = "删除示例")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "示例id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@EditableId(ExampleEntity.class) @PathVariable("id") Long id);

  class ExamplePageResult extends DataResponse<Page<ExampleEntity>> {

  }

  class ExampleResult extends DataResponse<ExampleEntity> {

  }
}