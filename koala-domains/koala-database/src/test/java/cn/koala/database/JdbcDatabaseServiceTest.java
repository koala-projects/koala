package cn.koala.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JdbcDatabaseServiceTest {

  @Test
  public void test() {
    JdbcDatabaseService jdbcDatabaseService = new JdbcDatabaseService();
    ConnectProperties connectProperties = new ConnectProperties(
      "jdbc:mysql://bj-cdb-9amt73r4.sql.tencentcdb.com:59997/koala",
      "test",
      "test@5015021301",
      "koala"
    );
    List<Table> tables = jdbcDatabaseService.getTables(connectProperties, new PrefixFilter("k_"));
    Assertions.assertEquals(tables.size(), 7);
    Assertions.assertEquals(tables.get(2).getColumns().size(), 2);
  }
}
