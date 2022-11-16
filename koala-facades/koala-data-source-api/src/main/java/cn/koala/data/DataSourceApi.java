package cn.koala.data;

import cn.koala.jdbc.Column;
import cn.koala.jdbc.Table;
import cn.koala.swagger.PageableAsQueryParam;
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
@RequestMapping("/api/data-sources")
@RestController
@Tag(name = "数据源管理")
public interface DataSourceApi {

  /**
   * 根据条件分页查询数据源
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 数据源列表
   */
  @Operation(summary = "根据条件分页查询数据源")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DataSourcePageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "数据源名称", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<DataSource>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                      @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询数据源
   *
   * @param id 数据源id
   * @return 数据源
   */
  @Operation(summary = "根据id查询数据源")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DataSourceResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据源id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<DataSource> loadById(@PathVariable("id") String id);

  /**
   * 创建数据源
   *
   * @param entity 数据源数据实体
   * @return 数据源
   */
  @Operation(summary = "创建数据源")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DataSourceResult.class))}
  )
  @PostMapping
  DataResponse<DataSource> create(@RequestBody DataSourceEntity entity);

  /**
   * 更新数据源
   *
   * @param id     数据源id
   * @param entity 数据源数据实体
   * @return 操作结果
   */
  @Operation(summary = "更新数据源")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据源id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody DataSourceEntity entity);

  /**
   * 删除数据源
   *
   * @param id 数据源id
   * @return 操作结果
   */
  @Operation(summary = "删除数据源")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据源id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  /**
   * 查询数据库名称列表
   *
   * @param id 数据源id
   * @return 数据源
   */
  @Operation(summary = "查询数据库名称列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = DataSourceCatalogNamesResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据源id", schema = @Schema(type = "string"))
  @GetMapping("{id}/catalog-names")
  DataResponse<List<String>> getCatalogNames(@PathVariable("id") String id);

  /**
   * 查询表列表
   *
   * @param id          数据源id
   * @param catalogName 数据库名称
   * @return 表列表
   */
  @Operation(summary = "查询表列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = DataSourceTablesResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据源id", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.PATH, name = "catalogName", description = "数据库名称", schema = @Schema(type = "string"))
  @GetMapping("{id}/catalogs/{catalogName}/tables")
  DataResponse<List<Table>> getTables(@PathVariable("id") String id, @PathVariable("catalogName") String catalogName);

  /**
   * 查询列列表
   *
   * @param id          数据源id
   * @param catalogName 数据库名称
   * @param tableName   表名
   * @return 列列表
   */
  @Operation(summary = "查询列列表")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = DataSourceColumnsResult.class))
  })
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据源id", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.PATH, name = "catalogName", description = "数据库名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.PATH, name = "tableName", description = "表名称", schema = @Schema(type = "string"))
  @GetMapping("{id}/catalogs/{catalogName}/tables/{tableName}/columns")
  DataResponse<List<Column>> getColumns(@PathVariable("id") String id, @PathVariable("catalogName") String catalogName,
                                        @PathVariable("tableName") String tableName);

  /**
   * 数据源是否可连接
   *
   * @param entity 数据实体
   * @return 是否可连接
   */
  @Operation(summary = "数据源是否可连接")
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = DataSourceIsConnectableResult.class))
  })
  @PostMapping("is-connectable")
  DataResponse<Boolean> isConnectable(@RequestBody DataSourceEntity entity);

  class DataSourcePageResult extends DataResponse<Page<DataSourceEntity>> {

  }

  class DataSourceResult extends DataResponse<DataSourceEntity> {

  }

  class DataSourceCatalogNamesResult extends DataResponse<List<String>> {

  }

  class DataSourceTablesResult extends DataResponse<List<Table>> {

  }

  class DataSourceColumnsResult extends DataResponse<List<Column>> {

  }

  class DataSourceIsConnectableResult extends DataResponse<Boolean> {

  }
}
