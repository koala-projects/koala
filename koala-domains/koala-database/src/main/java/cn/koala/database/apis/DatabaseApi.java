package cn.koala.database.apis;

import cn.koala.database.Database;
import cn.koala.database.DatabaseTable;
import cn.koala.database.SimpleDatabaseTable;
import cn.koala.database.entities.DatabaseEntity;
import cn.koala.openapi.PageableAsQueryParam;
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
 * 数据库接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/databases")
@Tag(name = "数据库管理")
public interface DatabaseApi {

  /**
   * 根据条件分页查询数据库
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 数据库列表
   */
  @Operation(operationId = "listDatabases", summary = "根据条件分页查询数据库")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DatabasePageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "数据库名称", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Database>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                    @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询数据库
   *
   * @param id 数据库id
   * @return 数据库
   */
  @Operation(operationId = "loadDatabase", summary = "根据id查询数据库")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DatabaseResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据库id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Database> load(@PathVariable("id") Long id);

  /**
   * 创建数据库
   *
   * @param entity 数据库数据实体
   * @return 数据库
   */
  @Operation(operationId = "createDatabase", summary = "创建数据库")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DatabaseResult.class))}
  )
  @PostMapping
  DataResponse<Database> add(@RequestBody DatabaseEntity entity);

  /**
   * 更新数据库
   *
   * @param id     数据库id
   * @param entity 数据库数据实体
   * @return 操作结果
   */
  @Operation(operationId = "updateDatabase", summary = "更新数据库")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据库id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody DatabaseEntity entity);

  /**
   * 删除数据库
   *
   * @param id 数据库id
   * @return 操作结果
   */
  @Operation(operationId = "deleteDatabase", summary = "删除数据库")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据库id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  /**
   * 查询数据库表列表
   *
   * @param id 数据库id
   * @return 数据库表列表
   */
  @Operation(operationId = "listDatabaseTables", summary = "查询数据库表列表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DatabaseTablesResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据库id", schema = @Schema(type = "integer"))
  @GetMapping("{id}/tables")
  DataResponse<List<DatabaseTable>> table(@PathVariable("id") Long id);

  /**
   * 根据表名查询数据库表
   *
   * @param id   数据库id
   * @param name 表名
   * @return 数据库表
   */
  @Operation(operationId = "loadDatabaseTable", summary = "根据表名查询数据库表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DatabaseTablesResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据库id", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.PATH, name = "name", description = "表名", schema = @Schema(type = "string"))
  @GetMapping("{id}/tables/{name}")
  DataResponse<DatabaseTable> table(@PathVariable("id") Long id, @PathVariable("name") String name);

  class DatabasePageResult extends DataResponse<Page<DatabaseEntity>> {

  }

  class DatabaseResult extends DataResponse<DatabaseEntity> {

  }

  class DatabaseTablesResult extends DataResponse<List<SimpleDatabaseTable>> {

  }
}
