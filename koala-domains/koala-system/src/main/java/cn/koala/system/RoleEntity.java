package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 角色数据实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "角色数据实体")
public class RoleEntity extends AbstractSystemEntity implements Role {
  @Schema(description = "角色代码")
  private String code;
  @Schema(description = "角色名称")
  private String name;
  @Schema(description = "角色描述")
  private String description;
}
