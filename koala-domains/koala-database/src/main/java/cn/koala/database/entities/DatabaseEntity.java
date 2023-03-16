package cn.koala.database.entities;

import cn.koala.database.Database;
import cn.koala.mybatis.YesNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据库数据实体类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DatabaseEntity implements Database {
  @Schema(description = "主键")
  protected Long id;
  @Schema(description = "数据库名称")
  protected String name;
  @Schema(description = "数据库连接")
  protected String url;
  @Schema(description = "数据库用户名")
  protected String username;
  @Schema(description = "数据库密码")
  protected String password;
  @Schema(description = "数据库目录")
  protected String catalog;
  @Schema(description = "数据库模式")
  protected String schema;
  @Schema(description = "是否启用")
  protected YesNo isEnable;
  @Schema(description = "是否系统")
  protected YesNo isSystem;
  @Schema(description = "是否删除")
  protected YesNo isDelete;
}
