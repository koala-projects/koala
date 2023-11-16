package cn.koala.database.entities;

import cn.koala.Koala;
import cn.koala.database.Database;
import cn.koala.persist.domain.YesNo;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库数据实体类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DatabaseEntity implements Database, Serializable {

  @Serial
  private static final long serialVersionUID = Koala.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  protected Long id;

  @Schema(description = "数据库名称")
  @NotBlank(message = "{database.name.not-blank}", groups = Create.class)
  @Size(min = 2, max = 20, message = "{database.name.size}")
  protected String name;

  @Schema(description = "数据库连接")
  @NotBlank(message = "{database.url.not-blank}", groups = Create.class)
  @Size(min = 1, max = 500, message = "{database.url.size}")
  protected String url;

  @Schema(description = "数据库用户名")
  @NotBlank(message = "{database.username.not-blank}", groups = Create.class)
  @Size(min = 1, max = 50, message = "{database.username.size}")
  protected String username;

  @Schema(description = "数据库密码")
  @NotBlank(message = "{database.password.not-blank}", groups = Create.class)
  @Size(min = 1, max = 100, message = "{database.password.size}")
  protected String password;

  @Schema(description = "数据库目录")
  @Size(min = 1, max = 100, message = "{database.catalog.size}")
  protected String catalog;

  @Schema(description = "数据库模式")
  @Size(min = 1, max = 100, message = "{database.schema.size}")
  protected String schema;

  @Schema(description = "是否启用")
  protected YesNo isEnabled;
  @Schema(description = "是否系统")
  protected YesNo isSystemic;
  @Schema(description = "是否删除")
  protected YesNo isDeleted;
}
