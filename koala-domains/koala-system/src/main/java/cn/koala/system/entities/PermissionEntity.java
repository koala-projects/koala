package cn.koala.system.entities;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.persist.validator.UniqueField;
import cn.koala.system.Permission;
import cn.koala.system.PermissionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 权限数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "权限数据实体")
@UniqueField(value = "code", message = "{permission.code.unique}")
@UniqueField(value = "name", message = "{permission.name.unique}")
public class PermissionEntity extends AbstractEntity implements Permission {

  @Schema(description = "权限代码")
  @NotBlank(message = "{permission.code.not-blank}")
  @Size(min = 4, max = 20, message = "{permission.code.size}")
  protected String code;

  @Schema(description = "权限名称")
  @NotBlank(message = "{permission.name.not-blank}")
  @Size(min = 2, max = 20, message = "{permission.name.size}")
  protected String name;

  @Schema(description = "权限类型")
  @NotNull(message = "{permission.type.not-null}")
  protected PermissionType type;

  @Schema(description = "权限图标")
  protected String icon;

  @Schema(description = "权限路径")
  protected String url;

  @Schema(description = "权限组件")
  protected String component;

  @Schema(description = "权限备注")
  @Size(max = 100, message = "{permission.remark.size}")
  protected String remark;

  @Schema(description = "上级权限id")
  protected Long parentId;
}
