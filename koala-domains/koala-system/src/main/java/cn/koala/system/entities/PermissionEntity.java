package cn.koala.system.entities;

import cn.koala.mybatis.BaseUniversalEntity;
import cn.koala.system.Permission;
import cn.koala.system.PermissionType;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class PermissionEntity extends BaseUniversalEntity implements Permission {
  @Schema(description = "权限代码")
  protected String code;
  @Schema(description = "权限名称")
  protected String name;
  @Schema(description = "权限类型")
  protected PermissionType type;
  @Schema(description = "权限图标")
  protected String icon;
  @Schema(description = "权限路径")
  protected String url;
  @Schema(description = "权限组件")
  protected String component;
  @Schema(description = "权限备注")
  protected String remark;
  @Schema(description = "上级权限id")
  protected Long parentId;
}
