package cn.koala.system.setting;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.system.util.SystemConstants;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;


/**
 * 设置实体类
 *
 * @author Koala Code Gen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "设置实体")
public class SettingEntity extends AbstractEntity<Long, Long> implements Setting, Serializable {

  @Serial
  private static final long serialVersionUID = SystemConstants.SERIAL_VERSION_UID;

  @Schema(description = "设置代码")
  @NotNull(message = "设置代码不允许为空", groups = {Create.class})
  @Size(max = 20, message = "设置代码长度不能大于20", groups = {Create.class})
  private String code;

  @Schema(description = "设置名称")
  @NotNull(message = "设置名称不允许为空", groups = {Create.class})
  @Size(max = 20, message = "设置名称长度不能大于20", groups = {Create.class})
  private String name;

  @Schema(description = "设置描述")
  @Size(max = 200, message = "设置描述长度不能大于200", groups = {Create.class})
  private String description;

  @Schema(description = "设置类型")
  @NotNull(message = "设置类型不允许为空", groups = {Create.class})
  @Size(max = 20, message = "设置类型长度不能大于20", groups = {Create.class})
  private SettingType type;

  @Schema(description = "设置值")
  @NotNull(message = "设置值不允许为空", groups = {Create.class})
  private String value;
}
