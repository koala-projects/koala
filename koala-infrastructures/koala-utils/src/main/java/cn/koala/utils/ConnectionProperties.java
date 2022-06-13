package cn.koala.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class ConnectionProperties {
  protected String url;
  protected String username;
  protected String password;
  protected String catalog;
}
