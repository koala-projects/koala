package cn.houtaroy.koala.sample.eucalyptus.models;

import cn.koala.eucalyptus.Domain;
import cn.koala.eucalyptus.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KoalaDomain extends Domain {
  protected Property id;

  @Override
  public void addProperties(Property property) {
    if (isId(property)) {
      id = property;
    } else {
      super.addProperties(property);
    }
  }

  protected boolean isId(Property property) {
    return "id".equalsIgnoreCase(property.getName());
  }
}
