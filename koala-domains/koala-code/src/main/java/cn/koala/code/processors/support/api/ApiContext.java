package cn.koala.code.processors.support.api;

import lombok.Data;
import lombok.experimental.SuperBuilder;

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
  private ApiParameters parameters;
}
