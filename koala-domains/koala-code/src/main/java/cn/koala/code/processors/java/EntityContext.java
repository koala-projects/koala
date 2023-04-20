package cn.koala.code.processors.java;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

/**
 * 实体类上下文
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class EntityContext {
  private Set<String> imports;
  private Set<String> interfaces;
  private Properties properties;

  @Data
  @SuperBuilder(toBuilder = true)
  public static class Properties {
    private Property id;
    private List<Property> others;
  }
}
