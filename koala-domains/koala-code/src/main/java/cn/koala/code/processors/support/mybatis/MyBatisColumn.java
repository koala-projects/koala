package cn.koala.code.processors.support.mybatis;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * MyBatis列
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class MyBatisColumn {
  private String columnName;
  private String propertyName;
}
