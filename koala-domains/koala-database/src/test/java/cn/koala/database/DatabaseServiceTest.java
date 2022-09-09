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
    JdbcService jdbcDatabaseService = new JdbcService();
    DataSourceService dataSourceService = new DataSourceService(dataSource);
    check(jdbcDatabaseService.getTables(connectProperties, new PrefixFilter("k_")));
    check(dataSourceService.getTables(connectProperties, new PrefixFilter("k_")));
  }

  public void check(List<Table> tables) {
    Assertions.assertEquals(tables.size(), 7);
    Assertions.assertEquals(tables.get(2).getColumns().size(), 2);
  }
}
