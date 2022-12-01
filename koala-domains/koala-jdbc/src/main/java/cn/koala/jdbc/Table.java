package cn.koala.jdbc;

import java.util.List;

/**
 * 表
 *
 * @author Houtaroy
 */
public interface Table {
  /**
   * 获取表名
   *
   * @return 表名
   */
  String getName();

  /**
   * 获取备注
   *
   * @return 备注
   */
  String getRemarks();

  /**
   * 获取列列表
   *
   * @return 列列表
   */
  List<? extends Column> getColumns();
}
