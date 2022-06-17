package cn.koala.eucalyptus;

import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class JdbcSearchParameter implements SearchParameter {
  protected JdbcDomainProperty property;
  protected String code;
  protected String name;
  protected String type;
  protected Operator operator;
}
