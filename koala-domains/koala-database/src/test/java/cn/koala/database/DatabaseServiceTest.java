package cn.koala.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DatabaseServiceTest {

  @Test
  public void test() {
    ConnectProperties connectProperties = new ConnectProperties(
      "jdbc:mysql://localhost:3306/koala",
      "root",
      "123456",
      "koala"
    );
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl(connectProperties.getUrl());
    dataSource.setUser(connectProperties.getUser());
    dataSource.setPassword(connectProperties.getPassword());
    dataSource.setDatabaseName(connectProperties.getCatalog());
    JdbcDatabaseService jdbcDatabaseService = new JdbcDatabaseService();
    DataSourceDatabaseService dataSourceService = new DataSourceDatabaseService(dataSource);
    check(jdbcDatabaseService.getTables(connectProperties, new PrefixTableFilter("t_")));
    check(dataSourceService.getTables(connectProperties, new PrefixTableFilter("t_")));
  }

  public void check(List<Table> tables) {
    Assertions.assertEquals(tables.size(), 9);
    Assertions.assertEquals(tables.get(0).getColumns().size(), 7);
  }
}
