package cn.koala.database;

/**
 * 数据库表列
 *
 * @author Houtaroy
 */
@Deprecated
public interface DatabaseTableColumn {
  String getName();

  Integer getType();

  Integer getSize();

  Integer getDecimalDigits();

  String getRemarks();

  Boolean getIsNullable();

  Boolean getIsAutoincrement();

  Boolean getIsPrimaryKey();
}
