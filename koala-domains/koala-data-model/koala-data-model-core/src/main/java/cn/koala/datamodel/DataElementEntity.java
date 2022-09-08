package cn.koala.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

/**
 * 持久化数据元
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DataElementEntity implements PersistentDataElement {
  protected String id;
  protected String code;
  protected String content;
  protected PropertyEntity property;
  protected DataEntity data;

  /**
   * 根据持久化属性生成持久化数据元
   *
   * @param property 持久化属性
   * @return 持久化数据元
   */
  public static DataElementEntity fromProperty(PropertyEntity property) {
    return DataElementEntity.builder()
      .code(property.getCode())
      .property(property)
      .build();
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setContent(Object content) {
    this.content = Optional.ofNullable(content).map(Object::toString).orElse(null);
  }
}
