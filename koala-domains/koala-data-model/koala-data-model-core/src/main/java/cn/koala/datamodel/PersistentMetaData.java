package cn.koala.datamodel;

import cn.koala.persistence.Idable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 持久化元数据
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PersistentMetaData implements MetaData, Idable<String> {
  protected String id;
  protected String code;
  protected String name;
  protected String description;
  protected List<PersistentProperty> properties;

  /**
   * 设置属性列表, 同时将每个属性的元数据设置为自身
   *
   * @param properties 属性列表
   */
  public void setProperties(List<PersistentProperty> properties) {
    properties.forEach(property -> property.setMetaData(this));
    this.properties = properties;
  }
}
