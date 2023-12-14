package cn.koala.database.domain;

/**
 * 数据库表列
 *
 * @author Houtaroy
 */
public interface DatabaseTableColumn {

  String ID_NAME = "id";

  String getName();

  Integer getType();

  Integer getSize();

  Integer getDecimalDigits();

  String getRemarks();

  Boolean getIsNullable();

  Boolean getIsAutoincrement();

  Boolean getIsPrimaryKey();

  default boolean isId() {
    return ID_NAME.equals(getName());
  }
}
