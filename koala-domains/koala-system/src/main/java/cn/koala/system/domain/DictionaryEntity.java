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
 * 字典实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典实体")
public class DictionaryEntity extends AbstractEntity<Long, Long> implements Dictionary, Serializable {

  @Serial
  private static final long serialVersionUID = SystemConstants.SERIAL_VERSION_UID;

  @Schema(description = "字典代码")
  @NotBlank(message = "字典代码不能为空", groups = Create.class)
  @Size(min = 4, max = 20, message = "字典代码长度必须在4-20之间")
  protected String code;

  @Schema(description = "字典名称")
  @NotBlank(message = "字典名称不能为空", groups = Create.class)
  @Size(min = 2, max = 20, message = "字典名称长度必须在2-20之间", groups = Create.class)
  protected String name;

  @Schema(description = "字典描述")
  @Size(max = 200, message = "字典描述长度不能超过200", groups = Create.class)
  protected String description;
}
