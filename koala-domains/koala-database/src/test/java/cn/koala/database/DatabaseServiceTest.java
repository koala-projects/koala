package cn.koala.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DatabaseServiceTest {

  @Test
  public void test() {
    ConnectProperties connectProperties = new ConnectProperties(
      "jdbc:mysql://bj-cdb-9amt73r4.sql.tencentcdb.com:59997/koala",
      "test",
      "test@5015021301",
      "koala"
    );
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl(connectProperties.getUrl());
    dataSource.setUser(connectProperties.getUser());
    dataSource.setPassword(connectProperties.getPassword());
    dataSource.setDatabaseName(connectProperties.getCatalog());
    JdbcDatabaseService jdbcDatabaseService = new JdbcDatabaseService();
    DataSourceDatabaseService dataSourceService = new DataSourceDatabaseService(dataSource);
    check(jdbcDatabaseService.getTables(connectProperties, new PrefixTableFilter("k_")));
    check(dataSourceService.getTables(connectProperties, new PrefixTableFilter("k_")));
  }

  public void check(List<Table> tables) {
    Assertions.assertEquals(tables.size(), 7);
    Assertions.assertEquals(tables.get(2).getColumns().size(), 2);
  }
}
