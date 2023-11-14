package cn.koala.system.model;

import cn.koala.common.Koala;
import cn.koala.mybatis.AbstractEntity;
import cn.koala.persist.validator.UniqueField;
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
 * 角色数据实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "角色数据实体")
@UniqueField(value = "code", message = "{role.code.unique}")
@UniqueField(value = "name", message = "{role.name.unique}")
public class RoleEntity extends AbstractEntity<Long> implements Role, Serializable {

  @Serial
  private static final long serialVersionUID = Koala.SERIAL_VERSION_UID;

  @Schema(description = "角色代码")
  @NotBlank(message = "{role.code.not-blank}")
  @Size(min = 4, max = 20, message = "{role.code.size}")
  protected String code;

  @Schema(description = "角色名称")
  @NotBlank(message = "{role.name.not-blank}")
  @Size(min = 2, max = 20, message = "{role.name.size}")
  protected String name;

  @Schema(description = "角色备注")
  @Size(max = 100, message = "{role.remark.size}")
  protected String remark;
}
