package cn.koala.database.apis;

import cn.koala.database.Table;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据库表接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/tables")
@Tag(name = "数据库表接口")
public interface TableApi {
  /**
   * 查询数据库表
   *
   * @return 数据库表列表
   */
  @Operation(operationId = "listTables", summary = "查询数据库表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TablesResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "名称", schema = @Schema(type = "string"))
  @GetMapping
  DataResponse<List<Table>> list(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters);

  /**
   * 根据名称查询数据库表
   *
   * @param name 数据库表名称
   * @return 数据库表
   */
  @Operation(summary = "根据名称查询数据库表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TableResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "name", description = "数据库表名称", schema = @Schema(type = "string"))
  @GetMapping("{name}")
  DataResponse<Table> load(@PathVariable("name") String name);

  class TablesResult extends DataResponse<List<Table>> {

  }

  class TableResult extends DataResponse<Table> {

  }
}
