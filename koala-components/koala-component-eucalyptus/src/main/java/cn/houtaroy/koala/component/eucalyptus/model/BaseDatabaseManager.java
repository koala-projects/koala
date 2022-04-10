package cn.houtaroy.koala.component.eucalyptus.model;

import lombok.Data;

import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
@Data
public abstract class BaseDatabaseManager implements DatabaseManager {

  protected final Set<DatabaseDriver> drivers;

  /**
   * 构造函数
   */
  public BaseDatabaseManager() {
    this.drivers = new CopyOnWriteArraySet<>();
  }

  @Override
  public void addDriver(DatabaseDriver driver) throws SQLException {
    this.drivers.add(driver);
  }
}
