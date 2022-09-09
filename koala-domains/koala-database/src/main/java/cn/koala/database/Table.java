package cn.koala.database;

import java.util.List;

/**
 * 数据库表定义
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
   * 获取表备注
   *
   * @return 表备注
   */
  String getRemarks();

  /**
   * 获取表的全部列
   *
   * @return 表的全部列
   */
  List<? extends Column> getColumns();
}
