package cn.koala.system;

import cn.koala.enhancement.YesNo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
  @JsonIgnore
  private YesNo isEnable;
  @Schema(description = "是否系统")
  private YesNo isSystem;
  @JsonIgnore
  private YesNo isDelete;
}
