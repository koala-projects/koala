package cn.koala.eucalyptus;

import cn.koala.utils.Word;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class JdbcDomain implements Domain {
  protected Word code;
  protected String name;
  protected List<JdbcDomainProperty> properties;
  protected String tableName;
  protected JdbcDomainProperty idProperty;

  public String getCode() {
    return code.getValue();
  }
}
