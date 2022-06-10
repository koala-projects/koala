package cn.koala.eucalyptus;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 简单的领域模型
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SimpleDomain implements Domain {
  protected String name;
  protected String code;
  protected SimpleDomainProperty idProperty;
  protected List<SimpleDomainProperty> properties;
}
