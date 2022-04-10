package cn.houtaroy.koala.component.eucalyptus.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
public class JdbcDatabaseManager extends BaseDatabaseManager {

  @Override
  public void addDriver(DatabaseDriver driver) throws SQLException {
    DriverManager.registerDriver(driver.getDriver());
    super.addDriver(driver);
  }

  @Override
  public void removeDriver(DatabaseDriver driver) throws SQLException {
    if (driver != null) {
      DriverManager.deregisterDriver(driver.getDriver());
      drivers.remove(driver);
    }
  }

  @Override
  public Set<Table> getTables(String url) throws SQLException {
    Set<Table> result = new HashSet<>();
    DriverManager.getConnection(url);
    return result;
  }
}
