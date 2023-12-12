package cn.koala.database.domain;

import java.util.List;

/**
 * 数据库表
 *
 * @author Houtaroy
 */
public interface DatabaseTable {
  String getName();

  String getRemarks();

  List<? extends DatabaseTableColumn> getColumns();
}
