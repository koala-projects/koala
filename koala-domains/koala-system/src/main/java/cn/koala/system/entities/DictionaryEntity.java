package cn.koala.system.entities;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.persist.validator.UniqueField;
import cn.koala.system.Dictionary;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典数据实体")
@UniqueField(value = "code", message = "{dictionary.code.unique}")
@UniqueField(value = "name", message = "{dictionary.name.unique}")
public class DictionaryEntity extends AbstractEntity<Long> implements Dictionary {

  @Schema(description = "字典代码")
  @NotBlank(message = "{dictionary.code.not-blank}")
  @Size(min = 4, max = 20, message = "{dictionary.code.size}")
  protected String code;

  @Schema(description = "字典名称")
  @NotBlank(message = "{dictionary.name.not-blank}")
  @Size(min = 2, max = 20, message = "{dictionary.name.size}")
  protected String name;

  @Schema(description = "字典备注")
  @Size(max = 100, message = "{dictionary.remark.size}")
  protected String remark;
}
