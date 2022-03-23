package cn.houtaroy.koala.mybatis;


import cn.houtaroy.koala.models.ValueNameEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ValueNameEnum类型转换器
 *
 * @param <E> 枚举的泛型
 * @author shihongjun
 */
@MappedTypes(ValueNameEnum.class)
public class ValueNameEnumTypeHandler<E extends ValueNameEnum> extends BaseTypeHandler<ValueNameEnum> {

  private final Class<E> type;

  /**
   * 构造方法
   *
   * @param type 枚举类型
   */
  public ValueNameEnumTypeHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ValueNameEnum parameter, JdbcType jdbcType)
    throws SQLException {
    ps.setInt(i, parameter.getValue());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int code = rs.getInt(columnName);
    return rs.wasNull() ? null : valueOf(code);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int code = rs.getInt(columnIndex);
    return rs.wasNull() ? null : valueOf(code);
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int code = cs.getInt(columnIndex);
    return cs.wasNull() ? null : valueOf(code);
  }

  /**
   * 根据枚举值返回枚举示例
   *
   * @param code 枚举值
   * @return 枚举实例
   */
  private E valueOf(int code) {
    try {
      return ValueNameEnum.valueOf(type, code);
    } catch (Exception ex) {
      throw new IllegalArgumentException(
        "Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
    }
  }
}
