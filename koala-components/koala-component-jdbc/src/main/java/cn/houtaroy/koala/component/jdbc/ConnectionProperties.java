package cn.houtaroy.koala.component.jdbc;

import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class ConnectionProperties {
  protected String url;
  protected String username;
  protected String password;
  protected String catalog;
}
