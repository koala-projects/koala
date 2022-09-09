package cn.koala.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 连接属性
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConnectProperties {
  protected String url;
  protected String user;
  protected String password;
  protected String catalog;
}
