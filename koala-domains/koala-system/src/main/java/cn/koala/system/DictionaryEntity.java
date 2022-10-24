package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典数据实体")
public class DictionaryEntity implements Dictionary {
  @Schema(description = "主键ID")
  private String id;
  @Schema(description = "字典代码")
  private String code;
  @Schema(description = "字典名称")
  private String name;
  @Schema(description = "字典描述")
  private String description;
}
