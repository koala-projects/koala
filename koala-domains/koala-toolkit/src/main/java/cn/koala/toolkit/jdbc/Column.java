package cn.koala.toolkit.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据库列类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Column {
  private String columnName;
  private Integer dataType;
  private Integer columnSize;
  private Integer decimalDigits;
  private String remarks;
  private Boolean isNullable;
  private Boolean isAutoincrement;
  private Boolean isPrimaryKey;
}
