package cn.koala.system.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.system.util.SystemConstants;
import cn.koala.validation.group.Create;
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
 * 岗位实体类
 *
 * @author Koala Code Gen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "岗位实体")
public class DutyEntity extends AbstractEntity<Long, Long> implements Duty, Serializable {

  @Serial
  private static final long serialVersionUID = SystemConstants.SERIAL_VERSION_UID;

  @NotBlank(message = "岗位代码不允许为空", groups = {Create.class})
  @Size(max = 20, message = "岗位代码长度不能大于20", groups = {Create.class})
  @Schema(description = "岗位代码")
  private String code;

  @NotBlank(message = "岗位名称不允许为空", groups = {Create.class})
  @Size(max = 20, message = "岗位名称长度不能大于20", groups = {Create.class})
  @Schema(description = "岗位名称")
  private String name;

  @Size(max = 200, message = "岗位描述长度不能大于200", groups = {Create.class})
  @Schema(description = "岗位描述")
  private String description;
}
