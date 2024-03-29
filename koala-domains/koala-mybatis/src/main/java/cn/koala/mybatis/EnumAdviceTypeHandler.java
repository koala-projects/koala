package cn.koala.mybatis;


import cn.koala.persist.domain.EnumAdvice;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.Assert;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举增强类型转换器
 * <p>
 * 支持在Mapper中直接使用枚举增强
 *
 * @param <E> 枚举的泛型
 * @author Houtaroy
 */
@MappedTypes(EnumAdvice.class)
public class EnumAdviceTypeHandler<E extends EnumAdvice> extends BaseTypeHandler<EnumAdvice> {
  private final Class<E> type;

  /**
   * 构造方法
   *
   * @param type 枚举类型
   */
  public EnumAdviceTypeHandler(Class<E> type) {
    Assert.notNull(type, "枚举类型不能为null");
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, EnumAdvice parameter, JdbcType jdbcType)
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
    E[] enumConstants = type.getEnumConstants();
    for (E e : enumConstants) {
      if (e.getValue() == code) {
        return e;
      }
    }
    return null;
  }
}
