package cn.koala.datamodel;

import cn.koala.persistence.Idable;
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
public class PersistentDataElement implements Idable<String>, DataElement {
  protected String id;
  protected String code;
  protected String content;
  protected PersistentProperty property;
  protected PersistentData data;
  protected PersistentMetaData metaData;

  public void setContent(String content) {
    this.content = content;
  }

  public void setContent(Object content) {
    this.content = Optional.ofNullable(content).map(Object::toString).orElse(null);
  }
}
