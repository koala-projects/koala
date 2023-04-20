package cn.koala.code.processors.java;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * MyBatis 上下文
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class MyBatisContext {
  private List<MyBatisColumn> columns;
  private boolean stateful;
  private boolean auditable;

  @Data
  @SuperBuilder(toBuilder = true)
  public static class MyBatisColumn {
    private String columnName;
    private String propertyName;
  }
}
