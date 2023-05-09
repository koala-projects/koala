package cn.koala.code.processors.support.api;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 接口参数
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class ApiParameter {
  private String name;
  private String description;
  private String type;
}
