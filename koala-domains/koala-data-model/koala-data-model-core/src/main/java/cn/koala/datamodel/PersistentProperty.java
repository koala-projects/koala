package cn.koala.datamodel;

import cn.koala.persistence.Idable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 持久化属性
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PersistentProperty implements Property, Idable<String> {
  protected String id;
  protected String code;
  protected String name;
  protected String description;
  protected PropertyType type;
  protected PersistentMetadata metadata;
}
