package cn.koala.code.apis;

import cn.koala.code.database.Table;
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
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 构建接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/code-generator")
@Tag(name = "代码生成器接口")
public interface GeneratorApi {
  /**
   * 查询全部数据库表
   *
   * @return 日志列表
   */
  @Operation(operationId = "listAllTables", summary = "查询全部数据库表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TablesResult.class))}
  )
  @GetMapping("tables")
  DataResponse<List<Table>> tables() throws SQLException;

  @Operation(operationId = "codeGeneratePreview", summary = "代码生成预览")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PreviewResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "table", description = "数据库表", schema = @Schema(type = "string"))
  @GetMapping("preview/{table}")
  DataResponse<Map<String, String>> preview(@PathVariable("table") String table);

  @Operation(operationId = "codeGenerateTest", summary = "代码生成测试")
  @GetMapping("test")
  void test() throws Exception;

  class TablesResult extends DataResponse<List<Table>> {

  }

  class PreviewResult extends DataResponse<Map<String, String>> {

  }
}
