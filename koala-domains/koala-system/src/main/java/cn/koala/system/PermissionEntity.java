package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 权限数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "权限数据实体")
public class PermissionEntity implements Permission {
  @Schema(description = "权限ID")
  private String id;
  @Schema(description = "权限代码")
  private String code;
  @Schema(description = "权限名称")
  private String name;
  @Schema(description = "权限描述")
  private String description;
  private PermissionEntity parent;
}
