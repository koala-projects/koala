package cn.koala.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Houtaroy
 */
@Getter
@NoArgsConstructor
public class ConnectionProperties {
  protected String url;
  protected String username;
  protected String password;
  protected String catalog;
}
