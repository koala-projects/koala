package cn.koala.jdbc;

import cn.koala.utils.ConnectionProperties;
import cn.koala.utils.JdbcTable;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Houtaroy
 */
@RequestMapping("/jdbc")
@RestController
@Tag(name = "jdbc", description = "JDBC接口")
public interface JdbcApi {
  /**
   * 查询数据库全部表
   *
   * @param properties 连接参数
   * @return 表列表
   */
  @Operation(summary = "查询数据库全部表", tags = {"jdbc"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = JdbcTableResult.class))}
  )
  @GetMapping
  DataResponse<List<JdbcTable>> tables(ConnectionProperties properties);

  class JdbcTableResult extends DataResponse<List<JdbcTable>> {

  }
}
