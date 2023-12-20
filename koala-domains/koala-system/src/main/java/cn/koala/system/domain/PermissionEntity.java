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
 * 权限实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "权限实体")
public class PermissionEntity extends AbstractEntity<Long, Long> implements Permission, Serializable {

  @Serial
  private static final long serialVersionUID = SystemConstants.SERIAL_VERSION_UID;

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "上级权限id")
  private Long parentId;

  @Schema(description = "权限代码")
  @NotBlank(message = "权限代码不能为空")
  @Size(min = 4, max = 20, message = "权限代码长度必须在4-20之间")
  private String code;

  @Schema(description = "权限名称")
  @NotBlank(message = "权限名称不能为空")
  @Size(min = 2, max = 20, message = "权限名称长度必须在2-20之间")
  private String name;

  @Schema(description = "权限描述")
  @Size(max = 100, message = "权限描述长度不能超过100")
  private String description;
}
