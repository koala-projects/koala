package cn.koala.datamodel;

import cn.koala.persistence.Idable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * 持久化数据元
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DataElementEntity implements DataElement, Idable<String> {
  protected String id;
  protected String content;
  protected PropertyEntity property;

  /**
   * 根据属性实体生成数据元实体
   *
   * @param property 属性实体
   * @param content  数据内容
   * @return 数据元实体
   */
  public static DataElementEntity fromProperty(PropertyEntity property, Object content) {
    return DataElementEntity.builder()
      .content(Objects.toString(content, ""))
      .property(property)
      .build();
  }
}
