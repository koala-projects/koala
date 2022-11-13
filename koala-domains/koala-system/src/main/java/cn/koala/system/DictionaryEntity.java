package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典数据实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典数据实体")
public class DictionaryEntity extends AbstractSystemEntity implements Dictionary {
  @Schema(description = "字典代码")
  private String code;
  @Schema(description = "字典名称")
  private String name;
  @Schema(description = "字典描述")
  private String description;
}
