package cn.koala.database;

import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据库接口
 *
 * @author Houtaroy
 */
@RequestMapping("/database")
@RestController
@Tag(name = "database", description = "数据库接口")
public interface DatabaseApi {

  /**
   * 查询数据库表
   *
   * @param tablesRequest 请求参数
   * @return 用户列表
   */
  @Operation(summary = "查询数据库表", tags = {"database"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TableListResult.class))}
  )
  @PostMapping
  DataResponse<List<Table>> tables(@RequestBody TablesRequest tablesRequest);


  class TableListResult extends DataResponse<List<JdbcTable>> {

  }
}
