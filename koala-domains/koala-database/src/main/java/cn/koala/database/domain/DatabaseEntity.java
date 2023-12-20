package cn.koala.database.domain;

import cn.koala.database.util.DatabaseConstants;
import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DatabaseEntity extends AbstractEntity<Long, Long> implements Database, Serializable {

  @Serial
  private static final long serialVersionUID = DatabaseConstants.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "数据库名称")
  @NotBlank(message = "数据库名称不能为空", groups = Create.class)
  @Size(max = 20, message = "数据库名称长度不能超过20")
  private String name;

  @Schema(description = "数据库连接")
  @NotBlank(message = "数据库连接不能为空", groups = Create.class)
  @Size(max = 200, message = "数据库连接长度不能超过200")
  private String url;

  @Schema(description = "数据库用户名")
  @NotBlank(message = "数据库用户名不能为空", groups = Create.class)
  @Size(max = 20, message = "数据库用户名长度不能超过20")
  private String username;

  @Schema(description = "数据库密码")
  @NotBlank(message = "数据库密码不能为空", groups = Create.class)
  @Size(max = 20, message = "数据库密码长度不能超过20")
  private String password;

  @Schema(description = "数据库目录")
  @Size(max = 20, message = "数据库目录长度不能超过20")
  private String catalog;

  @Schema(description = "数据库模式")
  @Size(max = 20, message = "数据库模式长度不能超过20")
  private String schema;
}
