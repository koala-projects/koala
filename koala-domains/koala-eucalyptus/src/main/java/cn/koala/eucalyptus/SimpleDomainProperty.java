package cn.koala.eucalyptus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class SimpleDomainProperty implements DomainProperty {
  protected String code;
  protected String name;
  protected String type;
}
