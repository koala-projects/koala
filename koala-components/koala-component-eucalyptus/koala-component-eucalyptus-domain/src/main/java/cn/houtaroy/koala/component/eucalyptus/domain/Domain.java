package cn.houtaroy.koala.component.eucalyptus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Domain {

  protected String name;
  protected String description;
  protected List<Property> properties;
  protected List<Parameter> parameters;
  protected List<Order> orders;
}
