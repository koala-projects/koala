package cn.houtaroy.koala.component.eucalyptus.model;

import java.util.Optional;
import java.util.Set;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
public interface DatabaseManager {

  /**
   * 获取所有驱动
   *
   * @return 驱动列表
   */
  Set<DatabaseDriver> getDrivers();

  /**
   * 获取驱动
   *
   * @param name 驱动名
   * @return 驱动
   */
  default Optional<DatabaseDriver> getDriver(String name) {
    return getDrivers().stream().filter(driver -> driver.getName().equals(name)).findFirst();
  }

  /**
   * 新增驱动
   *
   * @param driver 驱动实例
   * @throws Exception 添加异常
   */
  void addDriver(DatabaseDriver driver) throws Exception;

  /**
   * 删除驱动
   *
   * @param name 驱动名
   * @throws Exception 删除异常
   */
  default void removeDriver(String name) throws Exception {
    removeDriver(getDriver(name).orElse(null));
  }

  /**
   * 删除驱动
   *
   * @param driver 驱动实例
   * @throws Exception 删除异常
   */
  default void removeDriver(DatabaseDriver driver) throws Exception {
    getDrivers().remove(driver);
  }

  /**
   * 获取数据库所有表
   *
   * @param url 数据库连接
   * @return 数据库表列表
   * @throws Exception 获取异常
   */
  Set<Table> getTables(String url) throws Exception;
}
