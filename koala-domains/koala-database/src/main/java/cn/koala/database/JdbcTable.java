package cn.koala.database;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * JDBC数据库表
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "JDBC表")
public class JdbcTable implements Table {
  @Schema(description = "表名")
  protected String name;
  @Schema(description = "表备注")
  protected String remarks;
  @Schema(description = "表全部列")
  protected List<JdbcColumn> columns;
}
