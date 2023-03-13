package cn.koala.database;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 数据库表简单实现类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SimpleDatabaseTable implements DatabaseTable {
  private String name;
  private String remarks;
  private List<SimpleDatabaseTableColumn> columns;
}
