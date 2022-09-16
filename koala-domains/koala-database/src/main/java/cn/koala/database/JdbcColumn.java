package cn.koala.database;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.JDBCType;

/**
 * JDBC数据库列实体
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "JDBC列")
public class JdbcColumn implements Column {
  @Schema(description = "列名")
  protected String name;
  @Schema(description = "列数据类型")
  protected JDBCType type;
  @Schema(description = "列长度")
  protected Integer size;
  @Schema(description = "列小数长度")
  protected Integer decimalDigits;
  @Schema(description = "列备注")
  protected String remarks;
  @Schema(description = "是否允许为空")
  protected boolean nullable;
  @Schema(description = "是否自增")
  protected boolean autoIncrement;
  @Schema(description = "是否主键")
  protected boolean primaryKey;
}
