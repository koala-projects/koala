package cn.koala.database;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据库表列简单实现类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SimpleDatabaseTableColumn implements DatabaseTableColumn {
  private String name;
  private Integer type;
  private Integer size;
  private Integer decimalDigits;
  private String remarks;
  private Boolean isNullable;
  private Boolean isAutoincrement;
  private Boolean isPrimaryKey;
}
