package cn.koala.builder;

import cn.koala.jdbc.Column;
import cn.koala.jdbc.JdbcColumn;
import cn.koala.jdbc.JdbcTable;
import cn.koala.jdbc.Table;
import cn.koala.lang.Naming;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.JDBCType;
import java.util.List;
import java.util.Map;

/**
 * 增强表领域转换器
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@Data
public class EnhancedTableDomainConverter implements DomainConverter {
  public static final Map<String, String> JAVA_TYPE_CONVERTER = Map.of(
    JDBCType.SMALLINT.getName(), "Integer",
    JDBCType.INTEGER.getName(), "Integer",
    JDBCType.BIGINT.getName(), "Long",
    JDBCType.FLOAT.getName(), "Float",
    JDBCType.DOUBLE.getName(), "Double",
    JDBCType.DECIMAL.getName(), "BigDecimal",
    JDBCType.DATE.getName(), "LocalDateTime",
    JDBCType.TIME.getName(), "LocalDateTime",
    JDBCType.TIMESTAMP.getName(), "LocalDateTime",
    JDBCType.BOOLEAN.getName(), "Boolean"
  );

  private String id = "enhanced-table";
  private String name = "增强表领域转换器";
  private List<DomainProperty> properties = List.of(
    new DomainProperty("name", "字符串", "数据库表的名称"),
    new DomainProperty("naming", "命名", "数据库表的命名, 支持多种风格, 详情参照Naming"),
    new DomainProperty("remarks", "字符串", "数据库表的备注"),
    new DomainProperty("primaryKey", "列对象", "数据库表的主键列"),
    new DomainProperty("enhancedColumns[1]", "增强列对象", "数据库表的某一列"),
    new DomainProperty("enhancedColumns[1].name", "字符串", "数据库列的名称"),
    new DomainProperty("enhancedColumns[1].naming", "命名", "数据库列的命名"),
    new DomainProperty("enhancedColumns[1].type", "字符串", "数据库列的JDBC类型的名称"),
    new DomainProperty("enhancedColumns[1].javaType", "字符串", "数据库列对应的Java类型名称"),
    new DomainProperty("enhancedColumns[1].size", "整型", "数据库列的长度"),
    new DomainProperty("enhancedColumns[1].decimalDigits", "整型", "数据库列的小数长度"),
    new DomainProperty("enhancedColumns[1].remarks", "字符串", "数据库列的备注"),
    new DomainProperty("enhancedColumns[1].nullable", "布尔", "数据库列是否允许为空"),
    new DomainProperty("enhancedColumns[1].autoIncrement", "布尔", "数据库列是否自增"),
    new DomainProperty("enhancedColumns[1].primaryKey", "布尔", "数据库列是否主键")
  );

  @Override
  public Object convert(Table table) {
    if (table instanceof JdbcTable jdbcTable) {
      return enhance(jdbcTable);
    }
    throw new IllegalStateException("转换对象应为JdbcTable或其子类");
  }

  /**
   * 实际转换逻辑
   *
   * @param table JdbcTable
   * @return 增强表对象
   */
  protected EnhancedTable enhance(JdbcTable table) {
    List<EnhancedColumn> columns = table.getColumns().stream().map(this::enhance).toList();
    EnhancedTable result = new EnhancedTable();
    BeanUtils.copyProperties(table, result);
    result.setEnhancedColumns(columns);
    result.setNaming(Naming.fromUnderScore(table.getName().toLowerCase()));
    result.setPrimaryKey(columns.stream().filter(Column::isPrimaryKey).findFirst().orElse(null));
    return result;
  }

  /**
   * 增加数据库列
   *
   * @param column JdbcColumn
   * @return 增强列对象
   */
  protected EnhancedColumn enhance(JdbcColumn column) {
    EnhancedColumn result = new EnhancedColumn();
    BeanUtils.copyProperties(column, result);
    result.setNaming(Naming.fromUnderScore(column.getName().toLowerCase()));
    result.setJavaType(JAVA_TYPE_CONVERTER.get(column.getType()));
    return result;
  }
}
