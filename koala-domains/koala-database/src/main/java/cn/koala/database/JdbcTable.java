package cn.koala.database;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * JDBC数据库表
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@Schema(description = "JDBC表")
public class JdbcTable implements Table {
  @Schema(description = "表名")
  protected String name;
  @Schema(description = "表备注")
  protected String remarks;
  @Schema(description = "表全部列")
  protected List<JdbcColumn> columns;
}
