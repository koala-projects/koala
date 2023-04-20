package cn.koala.code.processors.java;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 参数
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class Parameter {
  private String name;
  private String description;
  private String type;
}
