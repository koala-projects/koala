package cn.koala.code.processors.support.mybatis;

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
}
