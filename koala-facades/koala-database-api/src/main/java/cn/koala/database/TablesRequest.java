package cn.koala.database;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 查询数据库表请求体
 *
 * @author Houtaroy
 */
@Getter
public class TablesRequest extends ConnectProperties {
  @Schema(description = "表前缀")
  protected String prefix;
}
