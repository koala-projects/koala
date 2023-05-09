package cn.koala.system.entities;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.system.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
public class RoleEntity extends AbstractEntity implements Role {
  @Schema(description = "角色代码")
  protected String code;
  @Schema(description = "角色名称")
  protected String name;
  @Schema(description = "角色备注")
  protected String remark;
}
