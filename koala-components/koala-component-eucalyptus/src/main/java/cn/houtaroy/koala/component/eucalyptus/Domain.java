package cn.houtaroy.koala.component.eucalyptus;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class Domain {
  protected String name;
  protected String description;
  protected List<Property> properties = new ArrayList<>();

  /**
   * 新增属性
   *
   * @param property 属性
   */
  public void addProperties(Property property) {
    properties.add(property);
  }
}
