package cn.koala.jdbc;

/**
 * @author Houtaroy
 */
public interface Column {
  /**
   * 获取列名
   *
   * @return 列名
   */
  String getName();

  /**
   * 获取列类型
   *
   * @return 列类型
   */
  String getType();

  /**
   * 获取列长度
   *
   * @return 列长度
   */
  Integer getSize();

  /**
   * 获取列小数长度
   *
   * @return 列小数长度
   */
  Integer getDecimalDigits();

  /**
   * 获取列备注
   *
   * @return 列备注
   */
  String getRemarks();

  /**
   * 是否允许为空
   *
   * @return 是否
   */
  boolean isNullable();

  /**
   * 是否主键
   *
   * @return 是否
   */
  boolean isPrimaryKey();
}
