package cn.koala.query;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.persist.validator.EditableId;
import cn.koala.query.support.QueryEntity;
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
 * 查询接口
 *
 * @author Koala Code Generator
 */
@RestController
@RequestMapping("/api/queries")
@Tag(name = "查询管理")
public interface QueryApi {

  /**
   * 根据条件分页查询查询
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 查询分页结果
   */
  @PreAuthorize("hasAuthority('query.read')")
  @Operation(operationId = "listQueries", summary = "根据条件分页查询查询定义")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QueryPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "查询名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "isEnabled", description = "是否启用", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdBy", description = "创建人ID", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdTime", description = "创建时间", schema = @Schema(type = "date-time"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Query>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                 @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询查询
   *
   * @param id 查询id
   * @return 查询数据实体
   */
  @PreAuthorize("hasAuthority('query.read')")
  @Operation(operationId = "loadQuery", summary = "根据id查询查询")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QueryResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "查询id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Query> load(@PathVariable("id") Long id);

  /**
   * 创建查询
   *
   * @param entity 查询数据实体
   * @return 查询数据实体
   */
  @PreAuthorize("hasAuthority('query.create')")
  @Operation(operationId = "createQuery", summary = "创建查询")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QueryResult.class))}
  )
  @PostMapping
  DataResponse<Query> create(@Validated(Create.class) @RequestBody Query entity);

  /**
   * 更新查询
   *
   * @param id     查询id
   * @param entity 查询数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('query.update')")
  @Operation(operationId = "updateQuery", summary = "更新查询")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "查询id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@EditableId(Query.class) @PathVariable("id") Long id,
                  @Validated(Update.class) @RequestBody Query entity);

  /**
   * 删除查询
   *
   * @param id 查询id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('query.delete')")
  @Operation(operationId = "deleteQuery", summary = "删除查询")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "查询id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@EditableId(Query.class) @PathVariable("id") Long id);

  /**
   * 执行查询
   *
   * @param id 查询id
   * @return 查询数据实体
   */
  @PreAuthorize("hasAuthority('query.execute')")
  @Operation(operationId = "executeQuery", summary = "执行查询")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExecuteResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "查询id", schema = @Schema(type = "integer"))
  @PageableAsQueryParam
  @GetMapping("{id}/execute")
  DataResponse<Page<Map<String, Object>>> execute(@PathVariable("id") Long id,
                                                  @RequestParam Map<String, Object> parameters,
                                                  @Parameter(hidden = true) Pageable pageable);

  class QueryPageResult extends DataResponse<Page<QueryEntity>> {

  }

  class QueryResult extends DataResponse<QueryEntity> {

  }

  class ExecuteResult extends DataResponse<Page<Map<String, Object>>> {

  }
}
