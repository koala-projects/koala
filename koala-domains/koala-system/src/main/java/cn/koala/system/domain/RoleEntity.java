package cn.koala.system.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.system.util.SystemConstants;
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
public class RoleEntity extends AbstractEntity<Long, Long> implements Role, Serializable {

  @Serial
  private static final long serialVersionUID = SystemConstants.SERIAL_VERSION_UID;

  @Schema(description = "角色代码")
  @NotBlank(message = "角色代码不能为空")
  @Size(min = 4, max = 20, message = "角色代码长度必须在4-20之间")
  private String code;

  @Schema(description = "角色名称")
  @NotBlank(message = "角色名称不能为空")
  @Size(min = 2, max = 20, message = "角色名称长度必须在2-20之间")
  private String name;

  @Schema(description = "角色描述")
  @Size(max = 200, message = "角色描述长度不能超过200")
  private String description;
}
