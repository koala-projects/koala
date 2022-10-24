package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 角色数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "角色数据实体")
public class RoleEntity implements Role {
  @Schema(description = "角色ID")
  private String id;
  @Schema(description = "角色代码")
  private String code;
  @Schema(description = "角色名称")
  private String name;
  @Schema(description = "角色描述")
  private String description;
  @Schema(description = "角色权限列表")
  private List<PermissionEntity> permissions;
}
