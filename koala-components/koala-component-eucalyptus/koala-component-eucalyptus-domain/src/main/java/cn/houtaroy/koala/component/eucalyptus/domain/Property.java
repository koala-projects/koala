package cn.houtaroy.koala.component.eucalyptus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class Property {

  protected String name;
  protected String type;
  protected String description;
}
