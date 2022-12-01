package cn.koala.builder;

import cn.koala.jdbc.JdbcColumn;
import cn.koala.lang.Naming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 增强数据库列
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EnhancedColumn extends JdbcColumn {
  private Naming naming;
  private String javaType;
}
