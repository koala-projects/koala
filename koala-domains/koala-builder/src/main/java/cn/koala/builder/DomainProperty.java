package cn.koala.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 领域属性
 *
 * @author Houtaroy
 */
@Data
@AllArgsConstructor
public class DomainProperty {
  private String key;
  private String type;
  private String description;
}
