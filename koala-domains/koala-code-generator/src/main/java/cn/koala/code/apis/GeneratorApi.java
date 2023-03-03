package cn.koala.code.apis;

import cn.koala.toolkit.jdbc.Table;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

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

  @Operation(operationId = "codeGenerateTest", summary = "代码生成测试")
  @GetMapping("test")
  void test() throws Exception;

  class TablesResult extends DataResponse<List<Table>> {

  }
}
