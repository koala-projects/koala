package cn.koala.code.processors.java;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 接口上下文
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class ApiContext {
  private String path;
  private String permission;
  private Parameters parameters;

  @Data
  @SuperBuilder(toBuilder = true)
  public static class Parameters {
    private Parameter id;
    private List<Parameter> others;
  }
}
