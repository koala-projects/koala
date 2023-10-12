package cn.koala.system.entities;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.system.DictionaryItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典项数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典项数据实体")
public class DictionaryItemEntity extends AbstractEntity<Long> implements DictionaryItem {

  @Schema(description = "字典项代码")
  @NotBlank(message = "{dictionary-item.code.not-blank}")
  @Size(min = 4, max = 20, message = "{dictionary-item.code.size}")
  protected String code;

  @Schema(description = "字典项名称")
  @NotBlank(message = "{dictionary-item.name.not-blank}")
  @Size(min = 2, max = 20, message = "{dictionary-item.name.size}")
  protected String name;

  @Schema(description = "字典项备注")
  @Size(max = 100, message = "{dictionary-item.remark.size}")
  protected String remark;

  @Schema(description = "字典id")
  protected Long dictionaryId;
}
