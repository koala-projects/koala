package cn.houtaroy.koala.component.eucalyptus.model;

import java.sql.Driver;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
public interface DatabaseDriver {

  /**
   * 驱动名称
   *
   * @return 驱动名称
   */
  String getName();

  /**
   * 驱动实例
   *
   * @return 驱动实例
   */
  Driver getDriver();
}
