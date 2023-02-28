package cn.koala.system.entities;

import cn.koala.system.DictionaryItem;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class DictionaryItemEntity extends BaseSystemEntity implements DictionaryItem {
  @Schema(description = "字典项代码")
  protected String code;
  @Schema(description = "字典项名称")
  protected String name;
  @Schema(description = "字典项备注")
  protected String remark;
  @Schema(description = "字典id")
  protected Long dictionaryId;
}
