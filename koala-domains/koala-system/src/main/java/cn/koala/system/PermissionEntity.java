package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 权限数据实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "权限数据实体")
public class PermissionEntity extends AbstractSystemEntity implements Permission {
  @Schema(description = "权限代码")
  private String code;
  @Schema(description = "权限名称")
  private String name;
  @Schema(description = "权限描述")
  private String description;
}
