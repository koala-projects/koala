package cn.koala.code.processors;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Java属性
 *
 * @author Houtaroy
 */
@Data
@AllArgsConstructor
public class DomainProperty {
  private String name;
  private String description;
  private String javaType;
  private String jsonType;
}
