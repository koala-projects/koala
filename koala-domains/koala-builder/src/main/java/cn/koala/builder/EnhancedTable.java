package cn.koala.builder;

import cn.koala.jdbc.JdbcTable;
import cn.koala.lang.Naming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 增强表
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EnhancedTable extends JdbcTable {
  private Naming naming;
  private EnhancedColumn primaryKey;
  private List<EnhancedColumn> enhancedColumns;
}
