package cn.koala.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jdbc连接参数
 *
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
