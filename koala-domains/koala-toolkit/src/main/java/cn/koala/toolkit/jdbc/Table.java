package cn.koala.toolkit.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库表类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {
  private String tableName;
  private String remarks;
}
