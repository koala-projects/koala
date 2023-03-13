package cn.koala.database;

/**
 * 数据库表列
 *
 * @author Houtaroy
 */
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
