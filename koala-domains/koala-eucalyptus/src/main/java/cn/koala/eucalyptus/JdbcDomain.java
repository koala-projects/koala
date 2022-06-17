package cn.koala.eucalyptus;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class JdbcDomain implements Domain {
  protected String code;
  protected String name;
  protected List<JdbcDomainProperty> properties;
  protected String tableName;
  protected String className;
  protected String pluralCode;
  protected JdbcDomainProperty idProperty;
}
