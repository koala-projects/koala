package cn.koala.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Houtaroy
 */
public class JdbcHelperTest {
  @Test
  public void test() throws SQLException {
    String url = "jdbc:mysql://localhost:3306";
    String username = "root";
    String password = "123456";
    Assertions.assertTrue(JdbcHelper.getCatalogNames(url, username, password).contains("koala"));
    List<Table> tables = JdbcHelper.getTables(url, username, password, "koala");
    Assertions.assertFalse(tables.isEmpty());
    List<Column> columns = JdbcHelper.getColumns(url, username, password, "koala", "t_role");
    Assertions.assertFalse(columns.isEmpty());
    Assertions.assertEquals(columns.get(0).getName(), "id");
    Assertions.assertTrue(columns.get(0).isPrimaryKey());
  }
}
