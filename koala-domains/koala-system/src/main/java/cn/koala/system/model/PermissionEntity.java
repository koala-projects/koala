package cn.koala.system.model;

import cn.koala.common.Koala;
import cn.koala.persist.validator.UniqueField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 权限数据实体类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "权限数据实体")
@UniqueField(value = "code", message = "{permission.code.unique}")
@UniqueField(value = "name", message = "{permission.name.unique}")
public class PermissionEntity implements Permission, Serializable {

  @Serial
  private static final long serialVersionUID = Koala.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  protected Long id;

  @Schema(description = "权限代码")
  @NotBlank(message = "{permission.code.not-blank}")
  @Size(min = 4, max = 20, message = "{permission.code.size}")
  protected String code;

  @Schema(description = "权限名称")
  @NotBlank(message = "{permission.name.not-blank}")
  @Size(min = 2, max = 20, message = "{permission.name.size}")
  protected String name;

  @Schema(description = "权限备注")
  @Size(max = 100, message = "{permission.remark.size}")
  protected String remark;

  @Schema(description = "上级权限id")
  protected Long parentId;

  @Schema(description = "排序索引")
  protected Long sortIndex;
}
