package cn.koala.database;

import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(description = "JDBC链接地址")
  protected String url;
  @Schema(description = "用户名")
  protected String user;
  @Schema(description = "密码")
  protected String password;
  @Schema(description = "数据库名称")
  protected String catalog;
}
