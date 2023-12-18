package cn.koala.system.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.system.util.Versions;
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 字典项实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典项实体")
public class DictionaryItemEntity extends AbstractEntity<Long, Long> implements DictionaryItem, Serializable {

  @Serial
  private static final long serialVersionUID = Versions.SERIAL;

  @Schema(description = "字典项代码")
  @NotBlank(message = "字典项代码不能为空", groups = Create.class)
  @Size(min = 4, max = 20, message = "字典项代码长度必须在4-20之间", groups = Create.class)
  protected String code;

  @Schema(description = "字典项名称")
  @NotBlank(message = "字典项名称不能为空", groups = Create.class)
  @Size(min = 2, max = 20, message = "字典项名称长度必须在2-20之间", groups = Create.class)
  protected String name;

  @Schema(description = "字典项备注")
  @Size(max = 200, message = "字典项备注长度不能超过200", groups = Create.class)
  protected String description;

  @Schema(description = "字典id")
  @NotNull(message = "字典id不能为空", groups = Create.class)
  protected Long dictionaryId;
}
